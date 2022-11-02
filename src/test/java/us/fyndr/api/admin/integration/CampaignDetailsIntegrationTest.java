package us.fyndr.api.admin.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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

import us.fyndr.api.admin.dto.CampaignDetailsInDTO;
import us.fyndr.api.admin.dto.CampaignDetailsOutDTO;
import us.fyndr.api.admin.dto.PagedCampaignDetailOutDTO;
import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql",
        "classpath:/dml/integration/campaign_details_insertion.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {
        "classpath:/dml/integration/campaign_details_deletion.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureWireMock(port = 9090)
@AutoConfigureWebTestClient(timeout = "10000")
@TestPropertySource(properties = { "token-service-url=http://localhost:9090/", })
public class CampaignDetailsIntegrationTest {

    @Autowired
    WireMockServer wireMockServer;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private WebTestClient webTestClient;

    private final LocalDateTime localDateTime = LocalDateTime.parse("2022-09-03T10:37:30");

    private final Instant endDate = localDateTime.toInstant(ZoneOffset.UTC);

    private final LocalDate currentTimeStamp = endDate.atZone(ZoneId.of("UTC")).toLocalDate();

    TokenUser tokenUser = new TokenUser();
    FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();

    @BeforeEach
    private void setup() throws Exception {

        TokenUser tokenUser = new TokenUser();

        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("FYNDR");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);

        stubFor(get("/v1/token/fetchData")
                .willReturn(ok().withStatus(200).withHeader(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(objectMapper.writeValueAsString(tokenUser))));

    }

    //@Test
    public void testCampaignDetails() throws Exception {

        String pgStart = "0";
        String pgSize = "4";
        String authToken = "authToken";

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();
        PagedCampaignDetailOutDTO pagedCampaignDetailsOutDTO = new PagedCampaignDetailOutDTO();
        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("a");
        campaignDetailsOutDTO.setCampaignName("campaignName");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.7);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType");
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");
        campaignDetailsOutDTO.setObjId(103l);

        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("b");
        campaignDetailsOutDTO.setCampaignName("campaignName2");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.7);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType1");
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");
        campaignDetailsOutDTO.setObjId(105l);

        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);
        campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("c");
        campaignDetailsOutDTO.setCampaignName("campaignName3");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.3);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType2");
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");
        campaignDetailsOutDTO.setObjId(106l);

        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("d");
        campaignDetailsOutDTO.setCampaignName("campaignName1");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.3);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType");
        campaignDetailsOutDTO.setCurrency("INR");
        campaignDetailsOutDTO.setCurrencySymbol("₹");
        campaignDetailsOutDTO.setObjId(104l);

        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        pagedCampaignDetailsOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        pagedCampaignDetailsOutDTO.setLast(true);
        pagedCampaignDetailsOutDTO.setCount(4L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedCampaignDetailsOutDTO);

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/campaign").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(campaignDetailsInDTO)).header("authorization", authToken).exchange()
                .expectStatus().isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }

    //@Test
    public void testPaginationCampaignDetails() throws Exception {

        String authToken = "authToken";

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();
        List<CampaignDetailsOutDTO> expectedCampaignDetailsOutDTOList = new ArrayList<>();

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("a");
        campaignDetailsOutDTO.setCampaignName("campaignName");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.7);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType");
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");
        campaignDetailsOutDTO.setObjId(103l);

        expectedCampaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("b");
        campaignDetailsOutDTO.setCampaignName("campaignName2");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.7);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType1");
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");
        campaignDetailsOutDTO.setObjId(105l);

        expectedCampaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        pagedCampaignDetailOutDTO.setCampaignDetails(expectedCampaignDetailsOutDTOList);
        pagedCampaignDetailOutDTO.setLast(false);
        pagedCampaignDetailOutDTO.setCount(4L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedCampaignDetailOutDTO);

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/campaign").queryParam("pgStart", 1).queryParam("pgSize", 2)
                        .build())
                .body(BodyInserters.fromValue(campaignDetailsInDTO)).header("authorization", authToken).exchange()
                .expectStatus().isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

        campaignDetailsInDTO = new CampaignDetailsInDTO();
        pagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();
        expectedCampaignDetailsOutDTOList = new ArrayList<>();

        campaignDetailsOutDTO.setBusinessName("c");
        campaignDetailsOutDTO.setCampaignName("campaignName3");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.3);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType2");
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");
        campaignDetailsOutDTO.setObjId(106l);

        expectedCampaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("d");
        campaignDetailsOutDTO.setCampaignName("campaignName1");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.3);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType");
        campaignDetailsOutDTO.setCurrency("INR");
        campaignDetailsOutDTO.setCurrencySymbol("₹");
        campaignDetailsOutDTO.setObjId(104l);

        expectedCampaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        pagedCampaignDetailOutDTO.setCampaignDetails(expectedCampaignDetailsOutDTOList);
        pagedCampaignDetailOutDTO.setLast(true);
        pagedCampaignDetailOutDTO.setCount(4L);

        exceptedResponseOutDTO = new ResponseOutDTO(true, pagedCampaignDetailOutDTO);

        actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/campaign").queryParam("pgStart", 2).queryParam("pgSize", 2)
                        .build())
                .body(BodyInserters.fromValue(campaignDetailsInDTO)).header("authorization", authToken).exchange()
                .expectStatus().isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

        campaignDetailsInDTO = new CampaignDetailsInDTO();
        pagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();
        expectedCampaignDetailsOutDTOList = new ArrayList<>();
        expectedCampaignDetailsOutDTOList = null;

        pagedCampaignDetailOutDTO.setCampaignDetails(expectedCampaignDetailsOutDTOList);
        pagedCampaignDetailOutDTO.setLast(true);
        pagedCampaignDetailOutDTO.setCount(4L);

        exceptedResponseOutDTO = new ResponseOutDTO(true, pagedCampaignDetailOutDTO);

        actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/campaign").queryParam("pgStart", 3).queryParam("pgSize", 2)
                        .build())
                .body(BodyInserters.fromValue(campaignDetailsInDTO)).header("authorization", authToken).exchange()
                .expectStatus().isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }

    //@Test
    public void testCountryFilterCampaignDetails() throws Exception {

        String authToken = "authToken";

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();
        campaignDetailsInDTO.setCountry("US");

        PagedCampaignDetailOutDTO pagedCampaignDetailsOutDTO = new PagedCampaignDetailOutDTO();
        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("b");
        campaignDetailsOutDTO.setCampaignName("campaignName2");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.7);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType1");
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);
        campaignDetailsOutDTO.setObjId(105l);

        pagedCampaignDetailsOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        pagedCampaignDetailsOutDTO.setLast(false);
        pagedCampaignDetailsOutDTO.setCount(3L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedCampaignDetailsOutDTO);

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/campaign").queryParam("pgStart", 2).queryParam("pgSize", 1)
                        .build())
                .body(BodyInserters.fromValue(campaignDetailsInDTO)).header("authorization", authToken).exchange()
                .expectStatus().isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

        campaignDetailsInDTO = new CampaignDetailsInDTO();
        campaignDetailsInDTO.setCountry("IN");
        pagedCampaignDetailsOutDTO = new PagedCampaignDetailOutDTO();
        campaignDetailsOutDTOList = new ArrayList<>();
        campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("d");
        campaignDetailsOutDTO.setCampaignName("campaignName1");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.3);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType");
        campaignDetailsOutDTO.setCurrency("INR");
        campaignDetailsOutDTO.setCurrencySymbol("₹");
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);
        campaignDetailsOutDTO.setObjId(104l);

        pagedCampaignDetailsOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        pagedCampaignDetailsOutDTO.setLast(true);
        pagedCampaignDetailsOutDTO.setCount(1L);

        exceptedResponseOutDTO = new ResponseOutDTO(true, pagedCampaignDetailsOutDTO);

        actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/campaign").queryParam("pgStart", 1).queryParam("pgSize", 1)
                        .build())
                .body(BodyInserters.fromValue(campaignDetailsInDTO)).header("authorization", authToken).exchange()
                .expectStatus().isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }

    //@Test
    public void testDateFilterCampaignDetails() throws Exception {

        String authToken = "authToken";

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();
        campaignDetailsInDTO.setStartDate(LocalDate.of(2022, 01, 1));
        campaignDetailsInDTO.setEndDate(LocalDate.of(2022, 10, 10));

        PagedCampaignDetailOutDTO pagedCampaignDetailsOutDTO = new PagedCampaignDetailOutDTO();
        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName("a");
        campaignDetailsOutDTO.setCampaignName("campaignName");
        campaignDetailsOutDTO.setCampaignType("campaignType");
        campaignDetailsOutDTO.setEndDate(currentTimeStamp);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(54.7);
        campaignDetailsOutDTO.setOfferSold(1);
        campaignDetailsOutDTO.setActiveOffers(1);
        campaignDetailsOutDTO.setTotalOffers(1);
        campaignDetailsOutDTO.setIndustryType("buisnessType");
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);
        campaignDetailsOutDTO.setObjId(103l);

        pagedCampaignDetailsOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        pagedCampaignDetailsOutDTO.setLast(false);
        pagedCampaignDetailsOutDTO.setCount(4L);

        ResponseOutDTO exceptedResponseOutDTO = new ResponseOutDTO(true, pagedCampaignDetailsOutDTO);

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/campaign").queryParam("pgStart", 1).queryParam("pgSize", 1)
                        .build())
                .body(BodyInserters.fromValue(campaignDetailsInDTO)).header("authorization", authToken).exchange()
                .expectStatus().isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);

        String expectedResult = objectMapper.writeValueAsString(exceptedResponseOutDTO);

        assertEquals(expectedResult, actualResult);

    }

}