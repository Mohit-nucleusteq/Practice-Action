package us.fyndr.api.admin.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.dto.RevenueStatisticsOutDTO;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql",
        "classpath:/dml/integration/revenue_statistics_invoice_insertion.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {
        "classpath:/dml/integration/revenue_statistics_invoice_deletion.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureWebTestClient(timeout = "100000")
@AutoConfigureWireMock(port = 9090)
@TestPropertySource(properties = { "token-service-url=http://localhost:9090/", })
public class RevenueStatisticsIntegrationTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    private void setup() throws Exception {

        TokenUser tokenUser = new TokenUser();
        FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();

        String accessCode = "accessCode";
        fetchUserTokenResponse.setAccessCode(accessCode);

        String accessCodeExpiry = "accessCodeExpiry";
        fetchUserTokenResponse.setAccessCodeExpiry(accessCodeExpiry);

        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("FYNDR");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);

        fetchUserTokenResponse.setTokenUser(tokenUser);

        stubFor(post("/v1/token/admin/fetchUserToken")
                .willReturn(ok().withStatus(200).withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(fetchUserTokenResponse))));
        stubFor(get("/v1/token/fetchData")
                .willReturn(ok().withStatus(200).withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(tokenUser))));

    }

    //@Test
    public void testRevenueStatistics() throws JsonProcessingException {
        RevenueStatisticsOutDTO expectedRevenueStatisticsOutDto = new RevenueStatisticsOutDTO();

        expectedRevenueStatisticsOutDto.setCatalogueRevenue(566.1);
        expectedRevenueStatisticsOutDto.setInteractionRevenue(206.12);
        expectedRevenueStatisticsOutDto.setOfferRevenue(25.41);
        expectedRevenueStatisticsOutDto.setPromotionalRevenue(30.22);
        expectedRevenueStatisticsOutDto.setTotalRevenue(827.85);
        expectedRevenueStatisticsOutDto.setCurrency("US");
        expectedRevenueStatisticsOutDto.setCurrencySymbol("#");

        ResponseOutDTO responseOutDto = new ResponseOutDTO(true, expectedRevenueStatisticsOutDto);

        String expectedResult = objectMapper.writeValueAsString(responseOutDto);
        String authToken = "authToken";

        ResponseOutDTO actualResponseOutDto = webTestClient.get().uri("/admin/statistics/revenue")
                .header("authorization", authToken).exchange().expectStatus().isOk().expectBody(ResponseOutDTO.class)
                .returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDto);

        assertEquals(expectedResult, actualResult);

    }

}
