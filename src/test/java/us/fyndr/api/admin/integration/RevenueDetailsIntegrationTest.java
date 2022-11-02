package us.fyndr.api.admin.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.WireMockServer;

import us.fyndr.api.admin.dto.PagedRevenueDetailsOutDTO;
import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.dto.RevenueDetailsInDTO;
import us.fyndr.api.admin.dto.RevenueDetailsOutDTO;
import us.fyndr.api.admin.model.TokenUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = {"classpath:/ddl/schema-ddl.sql", "classpath:/dml/integration/revenue_details_insertion.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:/dml/integration/revenue_details_deletion.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureWireMock(port = 9090)
@AutoConfigureWebTestClient(timeout = "30000")
@TestPropertySource(
        properties = {
                "token-service-url=http://localhost:9090/",
        }
        )
public class RevenueDetailsIntegrationTest {
        
    @Autowired
    WireMockServer wireMockServer;
    
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebTestClient webTestClient;
    
    @BeforeEach
    private void setup() throws Exception {
        
        TokenUser tokenUser = new TokenUser();
        
        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("FYNDR");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);

        stubFor(get("/v1/token/fetchData").willReturn(ok().withStatus(200).withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).withBody(objectMapper.writeValueAsString(tokenUser))));

    }

    //@Test
    public void testRevenueDetails() throws Exception{

        String pgStart = "0";
        String pgSize = "5";
       
        String authToken = "authToken";

        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName1");
        revenueDetailsOutDTO.setTotalRevenue(124.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setOffers(120.0);
        revenueDetailsOutDTO.setCatalog(4.0);
        revenueDetailsOutDTO.setObjId(7L);
        
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName2");
        revenueDetailsOutDTO.setTotalRevenue(37.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setOffers(37.0);
        revenueDetailsOutDTO.setObjId(8L);
        
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName3");
        revenueDetailsOutDTO.setTotalRevenue(20.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setCatalog(20.0);
        revenueDetailsOutDTO.setObjId(9L);

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName4");
        revenueDetailsOutDTO.setTotalRevenue(90.0);
        revenueDetailsOutDTO.setOffers(90.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setObjId(10L);
        
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);

        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(true);
        pagedRevenueDetailsOutDTO.setCount(4L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
                
        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                                                    .uri(uriBuilder -> uriBuilder
                                                            .path("/admin/revenue")
                                                            .queryParam("pgStart", pgStart)
                                                            .queryParam("pgSize", pgSize)
                                                            .build())
                                                    .body(BodyInserters.fromValue(revenueDetailsInDTO))
                                                    .header("authorization", authToken)
                                                    .exchange()
                                                    .expectStatus().isOk()
                                                    .expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }
    
    //@Test
    public void testPaginationRevenueDetails() throws Exception{

        String authToken = "authToken";

        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName1");
        revenueDetailsOutDTO.setTotalRevenue(124.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setOffers(120.0);
        revenueDetailsOutDTO.setCatalog(4.0);
        revenueDetailsOutDTO.setObjId(7L);

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
      
        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName2");
        revenueDetailsOutDTO.setTotalRevenue(37.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setOffers(37.0);
        revenueDetailsOutDTO.setObjId(8L);

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);

        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(false);
        pagedRevenueDetailsOutDTO.setCount(4L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
                
        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                                                    .uri(uriBuilder -> uriBuilder
                                                            .path("/admin/revenue")
                                                            .queryParam("pgStart", 0)
                                                            .queryParam("pgSize", 2)
                                                            .build())
                                                    .body(BodyInserters.fromValue(revenueDetailsInDTO))
                                                    .header("authorization", authToken)
                                                    .exchange()
                                                    .expectStatus().isOk()
                                                    .expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

        authToken = "authToken";

        revenueDetailsInDTO = new RevenueDetailsInDTO();
        pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        revenueDetailsOutDTOList = new ArrayList<>();

        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName3");
        revenueDetailsOutDTO.setTotalRevenue(20.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setCatalog(20.0);
        revenueDetailsOutDTO.setObjId(9L);
 
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName4");
        revenueDetailsOutDTO.setTotalRevenue(90.0);
        revenueDetailsOutDTO.setOffers(90.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setObjId(10L);
        
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
                
        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(true);
        pagedRevenueDetailsOutDTO.setCount(4L);

        exceptedResponseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
                
        actualResponseOutDTO = webTestClient.post()
                                                    .uri(uriBuilder -> uriBuilder
                                                            .path("/admin/revenue")
                                                            .queryParam("pgStart", 2)
                                                            .queryParam("pgSize", 2)
                                                            .build())
                                                    .body(BodyInserters.fromValue(revenueDetailsInDTO))
                                                    .header("authorization", authToken)
                                                    .exchange()
                                                    .expectStatus().isOk()
                                                    .expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);
        
        authToken = "authToken";

        revenueDetailsInDTO = new RevenueDetailsInDTO();
        pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        revenueDetailsOutDTOList = new ArrayList<>();

        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName3");
        revenueDetailsOutDTO.setTotalRevenue(20.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setCatalog(20.0);
        revenueDetailsOutDTO.setObjId(9L);
 
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
                
        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(false);
        pagedRevenueDetailsOutDTO.setCount(4L);

        exceptedResponseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
                
        actualResponseOutDTO = webTestClient.post()
                                                    .uri(uriBuilder -> uriBuilder
                                                            .path("/admin/revenue")
                                                            .queryParam("pgStart", 3)
                                                            .queryParam("pgSize", 1)
                                                            .build())
                                                    .body(BodyInserters.fromValue(revenueDetailsInDTO))
                                                    .header("authorization", authToken)
                                                    .exchange()
                                                    .expectStatus().isOk()
                                                    .expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);
                
        authToken = "authToken";

        pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
                      
        pagedRevenueDetailsOutDTO.setRevenueDetails(null);
        pagedRevenueDetailsOutDTO.setLast(true);
        pagedRevenueDetailsOutDTO.setCount(4L);

        exceptedResponseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
                
        actualResponseOutDTO = webTestClient.post()
                                                    .uri(uriBuilder -> uriBuilder
                                                            .path("/admin/revenue")
                                                            .queryParam("pgStart", 3)
                                                            .queryParam("pgSize", 2)
                                                            .build())
                                                    .body(BodyInserters.fromValue(revenueDetailsInDTO))
                                                    .header("authorization", authToken)
                                                    .exchange()
                                                    .expectStatus().isOk()
                                                    .expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }
    
    //@Test
    public void testDateFilterRevenueDetails() throws Exception{

        String pgStart = "0";
        String pgSize = "1";
        String authToken = "authToken";

        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        revenueDetailsInDTO.setStartDate(LocalDate.of(2022, 04, 01));
        revenueDetailsInDTO.setEndDate(LocalDate.of(2022, 06, 8));
                
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
      
        revenueDetailsOutDTO.setBusinessName("bizName1");
        revenueDetailsOutDTO.setTotalRevenue(120.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setOffers(120.0);
        revenueDetailsOutDTO.setCatalog(0.0);
        revenueDetailsOutDTO.setObjId(7L);
       
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);

        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(false);
        pagedRevenueDetailsOutDTO.setCount(2L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
                
        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                                                    .uri(uriBuilder -> uriBuilder
                                                            .path("/admin/revenue")
                                                            .queryParam("pgStart", pgStart)
                                                            .queryParam("pgSize", pgSize)
                                                            .build())
                                                    .body(BodyInserters.fromValue(revenueDetailsInDTO))
                                                    .header("authorization", authToken)
                                                    .exchange()
                                                    .expectStatus().isOk()
                                                    .expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }
    
    //@Test
    public void testCountryFilterRevenueDetails() throws Exception{

        String pgStart = "0";
        String pgSize = "2";
        String authToken = "authToken";

        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        revenueDetailsInDTO.setCountry("US");
                
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
      
        revenueDetailsOutDTO.setBusinessName("bizName1");
        revenueDetailsOutDTO.setTotalRevenue(124.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setOffers(120.0);
        revenueDetailsOutDTO.setCatalog(4.0);
        revenueDetailsOutDTO.setObjId(7L);
       
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName("bizName3");
        revenueDetailsOutDTO.setTotalRevenue(20.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setCatalog(20.0);
        revenueDetailsOutDTO.setObjId(9L);
       
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);

        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(false);
        pagedRevenueDetailsOutDTO.setCount(3L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
                
        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                                                    .uri(uriBuilder -> uriBuilder
                                                            .path("/admin/revenue")
                                                            .queryParam("pgStart", pgStart)
                                                            .queryParam("pgSize", pgSize)
                                                            .build())
                                                    .body(BodyInserters.fromValue(revenueDetailsInDTO))
                                                    .header("authorization", authToken)
                                                    .exchange()
                                                    .expectStatus().isOk()
                                                    .expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }
    
    //@Test
    public void testCountryandDateFilterRevenueDetails() throws Exception{

        String pgStart = "0";
        String pgSize = "2";
        String authToken = "authToken";

        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        revenueDetailsInDTO.setCountry("US");
        revenueDetailsInDTO.setStartDate(LocalDate.of(2022, 04, 01));
        revenueDetailsInDTO.setEndDate(LocalDate.of(2022, 06, 8));
                
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
      
        revenueDetailsOutDTO.setBusinessName("bizName1");
        revenueDetailsOutDTO.setTotalRevenue(120.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setOffers(120.0);
        revenueDetailsOutDTO.setObjId(7L);
       
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);

        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(true);
        pagedRevenueDetailsOutDTO.setCount(1L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
                
        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                                                    .uri(uriBuilder -> uriBuilder
                                                            .path("/admin/revenue")
                                                            .queryParam("pgStart", pgStart)
                                                            .queryParam("pgSize", pgSize)
                                                            .build())
                                                    .body(BodyInserters.fromValue(revenueDetailsInDTO))
                                                    .header("authorization", authToken)
                                                    .exchange()
                                                    .expectStatus().isOk()
                                                    .expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }
}
