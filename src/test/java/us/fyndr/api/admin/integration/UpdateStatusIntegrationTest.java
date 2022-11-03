package us.fyndr.api.admin.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.any;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.ObjectMapper;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dto.UpdateStatusInDTO;
import us.fyndr.api.admin.dto.UpdateStatusOutDTO;
import us.fyndr.api.admin.exception.UserBadRequestException;
import us.fyndr.api.admin.exception.UserNotFoundException;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;
import us.fyndr.api.admin.service.SNSService;
import us.fyndr.api.admin.util.AdminConstants;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql",
        "classpath:/dml/integration/search_and_update_status_user_insertion.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {
        "classpath:/dml/integration/search_and_update_status_user_deletion.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureWebTestClient(timeout = "100000")
@AutoConfigureWireMock(port = 9090)
@TestPropertySource(properties = { "token-service-url=http://localhost:9090/", })
public class UpdateStatusIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    SNSService snsService;

    @BeforeEach
    private void setup() throws Exception {

        FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();
        String accessCode = "accessCode";
        fetchUserTokenResponse.setAccessCode(accessCode);
        String accessCodeExpiry = "accessCodeExpiry";
        fetchUserTokenResponse.setAccessCodeExpiry(accessCodeExpiry);
        TokenUser tokenUser = new TokenUser();
        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("FYNDR");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);
        fetchUserTokenResponse.setTokenUser(tokenUser);
        stubFor(get("/v1/token/fetchData")
                .willReturn(ok().withStatus(200).withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(tokenUser))));
        stubFor(any(urlEqualTo("profile")).willReturn(ok().withStatus(200)));
    }

    //@Test
    public void testUpdateStatus() throws Exception {

        UpdateStatusInDTO updateStatusInDto = new UpdateStatusInDTO();
        updateStatusInDto.setAccountStatus(AccountStatus.SUSPENDED);

        UpdateStatusOutDTO expectedUpdateStatusOutDTO = new UpdateStatusOutDTO();
        expectedUpdateStatusOutDTO.setAccountStatus(AccountStatus.SUSPENDED);
        expectedUpdateStatusOutDTO.setMessage("Account status has been updated successfully");
        expectedUpdateStatusOutDTO.setSuccess(true);

        Long objId = 7L;
        String authToken = "authToken";
        doNothing().when(snsService).publishMessage(anyString(), anyString(), anyString());

        UpdateStatusOutDTO updateStatusOutDTO = webTestClient.put().uri("/admin/user/status/{objId}", objId)
                .header("authorization", authToken).body(BodyInserters.fromValue(updateStatusInDto)).exchange()
                .expectStatus().isOk().expectBody(UpdateStatusOutDTO.class).returnResult().getResponseBody();

        assertEquals(expectedUpdateStatusOutDTO, updateStatusOutDTO);
    }

    //@Test
    public void testUserNotFoundException() {

        UpdateStatusInDTO updateStatusInDto = new UpdateStatusInDTO();
        updateStatusInDto.setAccountStatus(AccountStatus.ACTIVE);

        Long objId = 2L;
        String authToken = "authToken";
        UserNotFoundException expectedUserNotFoundException = new UserNotFoundException(
                String.format(AdminConstants.USER_NOT_FOUND + " " + objId));

        UserNotFoundException userNotFoundException = webTestClient.put().uri("/admin/user/status/{objId}", objId)
                .header("authorization", authToken).body(BodyInserters.fromValue(updateStatusInDto)).exchange()
                .expectStatus().isNotFound().expectBody(UserNotFoundException.class).returnResult().getResponseBody();
        assertEquals(expectedUserNotFoundException.getMessage(), userNotFoundException.getMessage());
    }

    //@Test
    public void testUserBadRequestException() {

        UpdateStatusInDTO updateStatusInDto = new UpdateStatusInDTO();
        updateStatusInDto.setAccountStatus(AccountStatus.INACTIVE);

        Long objId = 1L;
        String authToken = "authToken";
        UserBadRequestException expectedUserNotFoundException = new UserBadRequestException(
                AdminConstants.INVALID_INDIVIDUAL_STATUS);

        UserBadRequestException userBadRequestException = webTestClient.put().uri("/admin/user/status/{objId}", objId)
                .header("authorization", authToken).body(BodyInserters.fromValue(updateStatusInDto)).exchange()
                .expectStatus().isBadRequest().expectBody(UserBadRequestException.class).returnResult()
                .getResponseBody();
        assertEquals(expectedUserNotFoundException.getMessage(), userBadRequestException.getMessage());
    }
}
