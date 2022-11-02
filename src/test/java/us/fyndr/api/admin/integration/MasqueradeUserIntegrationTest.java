package us.fyndr.api.admin.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.unauthorized;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static org.hamcrest.CoreMatchers.containsString;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;

import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;
import us.fyndr.api.admin.util.ErrorConstants;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:/ddl/schema-ddl.sql", "classpath:/dml/integration/masquerade_user_insertion.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:/dml/integration/masquerade_user_deletion.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureWireMock(port = 9090)
@AutoConfigureWebTestClient(timeout = "10000")
@TestPropertySource(
        properties = {
                "token-service-url=http://localhost:9090/",
        }
        )
public class MasqueradeUserIntegrationTest {
        
    @Autowired
    WireMockServer wireMockServer;
    
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebTestClient webTestClient;
    
    TokenUser tokenUser = new TokenUser();
    FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();

    @BeforeEach
    private void setup() throws Exception {
        
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
        
        stubFor(post("/v1/token/admin/fetchUserToken").willReturn(ok().withStatus(200).withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(objectMapper.writeValueAsString(fetchUserTokenResponse))));
        stubFor(get("/v1/token/fetchData").willReturn(ok().withStatus(200).withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(objectMapper.writeValueAsString(tokenUser))));

    }

    //@Test
    public void testMasqueradeAdminAsUser() throws Exception{
        
        String authToken = "authToken";

        Long objId = 1L;

        webTestClient
            .get()
            .uri("/admin/user/masquerade/{objId}", objId)
            .header("authorization", authToken)
            .exchange()
            .expectStatus().isOk()
            .expectBody(FetchUserTokenResponse.class).isEqualTo(fetchUserTokenResponse);

    }

    //@Test
    public void testNotFoundExceptionMasqueradeAdminAsUser() throws Exception{
        
        String authToken = "authToken";
        
        Long objId = 4L;
        
        webTestClient
        .get()
        .uri("/admin/user/masquerade/{objId}", objId)
        .header("authorization", authToken)
        .exchange()
        .expectStatus().isNotFound()
        .expectBody()
        .jsonPath("$.message").value(containsString(String.format(ErrorConstants.OBJECT_ID_NOT_FOUND, objId)));
    }
    
    //@Test
    public void testUnauthorizedExceptionMasqueradeAdminAsUser() throws Exception{
        
        String accessCode = "accessCode";
        fetchUserTokenResponse.setAccessCode(accessCode);
        
        String accessCodeExpiry = "accessCodeExpiry";
        fetchUserTokenResponse.setAccessCodeExpiry(accessCodeExpiry);

        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("BUSINESS");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);
        
        stubFor(get("/v1/token/fetchData").willReturn(ok().withStatus(200).withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(objectMapper.writeValueAsString(tokenUser))));
        String authToken = "authToken";
        
        Long objId = 4L;
        
        webTestClient
            .get()
            .uri("/admin/user/masquerade/{objId}", objId)
            .header("authorization", authToken)
            .exchange()
            .expectStatus().isUnauthorized()
            .expectBody()
            .jsonPath("$.message").value(containsString("Authentication Failed"));
    }  
    
    //@Test
    public void testInvalidUserMasqueradeAdminAsUser() throws Exception{
        
        stubFor(post("/v1/token/admin/fetchUserToken").willReturn(unauthorized()));
        
        String authToken = "authToken";

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

        Long objId = 1L;
        
        webTestClient
            .get()
            .uri("/admin/user/masquerade/{objId}", objId)
            .header("authorization", authToken)
            .exchange()
            .expectStatus().isUnauthorized()
            .expectBody() 
            .jsonPath("$.message").value(containsString("Only users with active and inactive status can be masquerade."));
    }
}

