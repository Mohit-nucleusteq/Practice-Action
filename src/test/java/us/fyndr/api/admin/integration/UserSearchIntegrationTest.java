package us.fyndr.api.admin.integration;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.google.common.net.HttpHeaders.CONTENT_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDateTime;
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
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.UserType;
import us.fyndr.api.admin.dto.AddressOutDTO;
import us.fyndr.api.admin.dto.IndividualOutDTO;
import us.fyndr.api.admin.dto.PagedIndividualOutDTO;
import us.fyndr.api.admin.dto.PhoneNumberOutDTO;
import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.dto.SearchInDTO;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql",
        "classpath:/dml/integration/search_user_insertion.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {
        "classpath:/dml/integration/search_user_cleanup.sql" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@AutoConfigureWebTestClient(timeout = "100000")
@AutoConfigureWireMock(port = 9090)
@TestPropertySource(properties = { "token-service-url=http://localhost:9090/", })
public class UserSearchIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    private final LocalDateTime localDateTime = LocalDateTime.parse("2022-09-03T10:37:30");

    private final Instant currentTimeStamp = localDateTime.toInstant(ZoneOffset.UTC);

    @Autowired
    private ObjectMapper objectMapper;

    int pgStart;
    int pgSize;

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
    }

    //@Test
    public void testSearchIndividualUsersForActiveStatus() throws Exception {

        String text = "";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);

        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.BUSINESS);

        SearchInDTO searchInDTO = setupSearchInDTO(text, accountStatusList, userTypeList);

        List<IndividualOutDTO> expectedIndividualOutDtoList = new ArrayList<>();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("addressline1");
        addressOutDTO.setAddressLine2("addressline2");
        addressOutDTO.setCity("city1");
        addressOutDTO.setCountry("US");
        addressOutDTO.setPostalCode("111011");
        addressOutDTO.setState("state1");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();
        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName("bizName2");
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("test@email3.com");
        expectedIndividualOutDto.setIsBusiness(true);
        expectedIndividualOutDto.setName("firstName3 lastName3");
        expectedIndividualOutDto.setObjId(3L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto);

        IndividualOutDTO expectedIndividualOutDto2 = new IndividualOutDTO();

        expectedIndividualOutDto2.setAddress(addressOutDTO);
        expectedIndividualOutDto2.setBusinessName("bizName2");
        expectedIndividualOutDto2.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto2.setEmail("test@email6.com");
        expectedIndividualOutDto2.setIsBusiness(true);
        expectedIndividualOutDto2.setName("firstName6 lastName6");
        expectedIndividualOutDto2.setObjId(6L);

        expectedIndividualOutDto2.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto2.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto2.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto2);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO.setLast(true);
        expectedPagedIndividualOutDTO.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO.setCount(2L);
        ResponseOutDTO responseOutDto = new ResponseOutDTO(true, expectedPagedIndividualOutDTO);

        Gson gson = new Gson();

        String expectedResult = objectMapper.writeValueAsString(responseOutDto);
        ResponseOutDTO expectedResponseOutDto = gson.fromJson(expectedResult, ResponseOutDTO.class);
        String authToken = "authToken";

        pgStart = 1;
        pgSize = 10;

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();
        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);
        ResponseOutDTO actualResponseOutDto = gson.fromJson(actualResult, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto, actualResponseOutDto);
    }

    //@Test
    public void testSearchIndividualUsersForActiveInactiveStatus() throws JsonProcessingException {

        String text = "";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);
        accountStatusList.add(AccountStatus.INACTIVE);
        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);
        userTypeList.add(UserType.BUSINESS);

        SearchInDTO searchInDTO = setupSearchInDTO(text, accountStatusList, userTypeList);

        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("addressline1");
        addressOutDTO.setAddressLine2("addressline2");
        addressOutDTO.setCity("city1");
        addressOutDTO.setCountry("US");
        addressOutDTO.setPostalCode("111011");
        addressOutDTO.setState("state1");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();

        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName("bizName2");
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("test@email3.com");
        expectedIndividualOutDto.setIsBusiness(true);
        expectedIndividualOutDto.setName("firstName3 lastName3");
        expectedIndividualOutDto.setObjId(3L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto);

        IndividualOutDTO expectedIndividualOutDto2 = new IndividualOutDTO();

        expectedIndividualOutDto2.setAddress(addressOutDTO);
        expectedIndividualOutDto2.setBusinessName("bizName2");
        expectedIndividualOutDto2.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto2.setEmail("test@email6.com");
        expectedIndividualOutDto2.setIsBusiness(true);
        expectedIndividualOutDto2.setName("firstName6 lastName6");
        expectedIndividualOutDto2.setObjId(6L);

        expectedIndividualOutDto2.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto2.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto2.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto2);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO.setLast(true);
        expectedPagedIndividualOutDTO.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO.setCount(3L);
        ResponseOutDTO responseOutDto = new ResponseOutDTO(true, expectedPagedIndividualOutDTO);

        Gson gson = new Gson();

        String expectedResult = objectMapper.writeValueAsString(responseOutDto);
        ResponseOutDTO expectedResponseOutDto = gson.fromJson(expectedResult, ResponseOutDTO.class);
        String authToken = "authToken";

        pgStart = 1;
        pgSize = 10;

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);
        ResponseOutDTO actualResponseOutDto = gson.fromJson(actualResult, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto, actualResponseOutDto);
    }

    //@Test
    public void testSearchIndividualUsersForActiveInactiveSuspendedStatus() throws Exception {
        String text = "";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);
        accountStatusList.add(AccountStatus.INACTIVE);
        accountStatusList.add(AccountStatus.SUSPENDED);
        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);
        userTypeList.add(UserType.BUSINESS);

        SearchInDTO searchInDTO = setupSearchInDTO(text, accountStatusList, userTypeList);

        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("addressline1");
        addressOutDTO.setAddressLine2("addressline2");
        addressOutDTO.setCity("city1");
        addressOutDTO.setCountry("US");
        addressOutDTO.setPostalCode("111011");
        addressOutDTO.setState("state1");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();

        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName("bizName2");
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("test@email3.com");
        expectedIndividualOutDto.setIsBusiness(true);
        expectedIndividualOutDto.setName("firstName3 lastName3");
        expectedIndividualOutDto.setObjId(3L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto);

        IndividualOutDTO expectedIndividualOutDto2 = new IndividualOutDTO();

        expectedIndividualOutDto2.setAddress(addressOutDTO);
        expectedIndividualOutDto2.setBusinessName("bizName2");
        expectedIndividualOutDto2.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto2.setEmail("test@email6.com");
        expectedIndividualOutDto2.setIsBusiness(true);
        expectedIndividualOutDto2.setName("firstName6 lastName6");
        expectedIndividualOutDto2.setObjId(6L);

        expectedIndividualOutDto2.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto2.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto2.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto2);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO.setLast(true);
        expectedPagedIndividualOutDTO.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO.setCount(3L);
        ResponseOutDTO responseOutDto = new ResponseOutDTO(true, expectedPagedIndividualOutDTO);

        Gson gson = new Gson();

        String expectedResult = objectMapper.writeValueAsString(responseOutDto);
        ResponseOutDTO expectedResponseOutDto = gson.fromJson(expectedResult, ResponseOutDTO.class);
        String authToken = "authToken";

        pgStart = 1;
        pgSize = 10;

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);
        ResponseOutDTO actualResponseOutDto = gson.fromJson(actualResult, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto, actualResponseOutDto);
    }

    //@Test
    public void testSearchIndividualAndBusinessUsersForAllAccountStatus() throws Exception {
        String text = "";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);
        accountStatusList.add(AccountStatus.INACTIVE);
        accountStatusList.add(AccountStatus.SUSPENDED);
        accountStatusList.add(AccountStatus.DELETED);

        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);
        userTypeList.add(UserType.BUSINESS);

        SearchInDTO searchInDTO = setupSearchInDTO(text, accountStatusList, userTypeList);
        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("addressline1");
        addressOutDTO.setAddressLine2("addressline2");
        addressOutDTO.setCity("city1");
        addressOutDTO.setCountry("US");
        addressOutDTO.setPostalCode("111011");
        addressOutDTO.setState("state1");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();

        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName("bizName2");
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("test@email3.com");
        expectedIndividualOutDto.setIsBusiness(true);
        expectedIndividualOutDto.setName("firstName3 lastName3");
        expectedIndividualOutDto.setObjId(3L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto);

        IndividualOutDTO expectedIndividualOutDto1 = new IndividualOutDTO();

        expectedIndividualOutDto1.setAddress(addressOutDTO);
        expectedIndividualOutDto1.setBusinessName(null);
        expectedIndividualOutDto1.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto1.setEmail("test@email5.com");
        expectedIndividualOutDto1.setIsBusiness(false);
        expectedIndividualOutDto1.setName("firstName5 lastName5");
        expectedIndividualOutDto1.setObjId(5L);

        expectedIndividualOutDto1.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto1.setStatus(AccountStatus.DELETED);
        expectedIndividualOutDto1.setWebsite(null);

        expectedIndividualOutDtoList.add(expectedIndividualOutDto1);

        IndividualOutDTO expectedIndividualOutDto2 = new IndividualOutDTO();

        expectedIndividualOutDto2.setAddress(addressOutDTO);
        expectedIndividualOutDto2.setBusinessName("bizName2");
        expectedIndividualOutDto2.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto2.setEmail("test@email6.com");
        expectedIndividualOutDto2.setIsBusiness(true);
        expectedIndividualOutDto2.setName("firstName6 lastName6");
        expectedIndividualOutDto2.setObjId(6L);

        expectedIndividualOutDto2.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto2.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto2.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto2);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO.setLast(true);
        expectedPagedIndividualOutDTO.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO.setCount(4L);
        ResponseOutDTO responseOutDto = new ResponseOutDTO(true, expectedPagedIndividualOutDTO);

        Gson gson = new Gson();

        String expectedResult = objectMapper.writeValueAsString(responseOutDto);
        ResponseOutDTO expectedResponseOutDto = gson.fromJson(expectedResult, ResponseOutDTO.class);
        String authToken = "authToken";

        pgStart = 1;
        pgSize = 10;

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);
        ResponseOutDTO actualResponseOutDto = gson.fromJson(actualResult, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto, actualResponseOutDto);
    }

    //@Test
    public void testWithoutAnySearchCriteria() throws Exception {

        String text = new String();

        List<AccountStatus> accountStatusList = new ArrayList<>();
        List<UserType> userTypeList = new ArrayList<>();

        SearchInDTO searchInDTO = setupSearchInDTO(text, accountStatusList, userTypeList);
        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("addressline1");
        addressOutDTO.setAddressLine2("addressline2");
        addressOutDTO.setCity("city1");
        addressOutDTO.setCountry("US");
        addressOutDTO.setPostalCode("111011");
        addressOutDTO.setState("state1");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();

        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName("bizName2");
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("test@email3.com");
        expectedIndividualOutDto.setIsBusiness(true);
        expectedIndividualOutDto.setName("firstName3 lastName3");
        expectedIndividualOutDto.setObjId(3L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto);

        IndividualOutDTO expectedIndividualOutDto1 = new IndividualOutDTO();

        expectedIndividualOutDto1.setAddress(addressOutDTO);
        expectedIndividualOutDto1.setBusinessName(null);
        expectedIndividualOutDto1.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto1.setEmail("test@email5.com");
        expectedIndividualOutDto1.setIsBusiness(false);
        expectedIndividualOutDto1.setName("firstName5 lastName5");
        expectedIndividualOutDto1.setObjId(5L);

        expectedIndividualOutDto1.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto1.setStatus(AccountStatus.DELETED);
        expectedIndividualOutDto1.setWebsite(null);

        expectedIndividualOutDtoList.add(expectedIndividualOutDto1);

        IndividualOutDTO expectedIndividualOutDto2 = new IndividualOutDTO();

        expectedIndividualOutDto2.setAddress(addressOutDTO);
        expectedIndividualOutDto2.setBusinessName("bizName2");
        expectedIndividualOutDto2.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto2.setEmail("test@email6.com");
        expectedIndividualOutDto2.setIsBusiness(true);
        expectedIndividualOutDto2.setName("firstName6 lastName6");
        expectedIndividualOutDto2.setObjId(6L);

        PhoneNumberOutDTO phoneNumberOutDTO2 = new PhoneNumberOutDTO();
        phoneNumberOutDTO2.setCountryCode("+91");

        expectedIndividualOutDto2.setPhone(phoneNumberOutDTO2);
        expectedIndividualOutDto2.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto2.setWebsite("testwebsite2");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto2);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO.setLast(true);
        expectedPagedIndividualOutDTO.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO.setCount(4L);
        ResponseOutDTO responseOutDto = new ResponseOutDTO(true, expectedPagedIndividualOutDTO);

        Gson gson = new Gson();

        String expectedResult = objectMapper.writeValueAsString(responseOutDto);

        ResponseOutDTO expectedResponseOutDto = gson.fromJson(expectedResult, ResponseOutDTO.class);

        String authToken = "authToken";

        pgStart = 1;
        pgSize = 10;

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);
        ResponseOutDTO actualResponseOutDto = gson.fromJson(actualResult, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto, actualResponseOutDto);
    }

    //@Test
    public void testDifferentPageScenarios() throws JsonProcessingException {

        String text = "";
        String country = "IND";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);

        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);

        SearchInDTO searchInDTO = setupSearchInDTO(text, accountStatusList, userTypeList);
        searchInDTO.setCountry(country);

        List<IndividualOutDTO> expectedIndividualOutDtoList = new ArrayList<>();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("addressline1");
        addressOutDTO.setAddressLine2("addressline2");
        addressOutDTO.setCity("city1");
        addressOutDTO.setCountry("IND");
        addressOutDTO.setPostalCode("111011");
        addressOutDTO.setState("state1");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();

        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName(null);
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("test@email7.com");
        expectedIndividualOutDto.setIsBusiness(false);
        expectedIndividualOutDto.setName("firstName7 lastName7");
        expectedIndividualOutDto.setObjId(7L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite(null);

        expectedIndividualOutDtoList.add(expectedIndividualOutDto);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO.setLast(false);
        expectedPagedIndividualOutDTO.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO.setCount(3L);

        Gson gson = new Gson();

        ResponseOutDTO responseOutDto = new ResponseOutDTO(true, expectedPagedIndividualOutDTO);
        String expectedResult = objectMapper.writeValueAsString(responseOutDto);

        ResponseOutDTO expectedResponseOutDto = gson.fromJson(expectedResult, ResponseOutDTO.class);

        String authToken = "authToken";

        pgStart = 1;
        pgSize = 1;

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);
        ResponseOutDTO actualResponseOutDto = gson.fromJson(actualResult, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto, actualResponseOutDto);

        List<IndividualOutDTO> expectedIndividualOutDtoList2 = new ArrayList<>();

        IndividualOutDTO expectedIndividualOutDto2 = new IndividualOutDTO();

        expectedIndividualOutDto2.setAddress(addressOutDTO);
        expectedIndividualOutDto2.setBusinessName(null);
        expectedIndividualOutDto2.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto2.setEmail("test@email8.com");
        expectedIndividualOutDto2.setIsBusiness(false);
        expectedIndividualOutDto2.setName("firstName8 lastName8");
        expectedIndividualOutDto2.setObjId(8L);

        expectedIndividualOutDto2.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto2.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto2.setWebsite(null);

        expectedIndividualOutDtoList2.add(expectedIndividualOutDto2);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO1.setLast(false);
        expectedPagedIndividualOutDTO1.setUsers(expectedIndividualOutDtoList2);
        expectedPagedIndividualOutDTO1.setCount(3L);

        ResponseOutDTO responseOutDto1 = new ResponseOutDTO(true, expectedPagedIndividualOutDTO1);
        String expectedResult1 = objectMapper.writeValueAsString(responseOutDto1);

        ResponseOutDTO expectedResponseOutDto1 = gson.fromJson(expectedResult1, ResponseOutDTO.class);

        pgStart = 2;
        pgSize = 1;

        ResponseOutDTO actualResponseOutDTO1 = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult1 = objectMapper.writeValueAsString(actualResponseOutDTO1);
        ResponseOutDTO actualResponseOutDto1 = gson.fromJson(actualResult1, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto1, actualResponseOutDto1);

        List<IndividualOutDTO> expectedIndividualOutDtoList3 = new ArrayList<>();

        IndividualOutDTO expectedIndividualOutDto3 = new IndividualOutDTO();

        expectedIndividualOutDto3.setAddress(addressOutDTO);
        expectedIndividualOutDto3.setBusinessName(null);
        expectedIndividualOutDto3.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto3.setEmail("test@email9.com");
        expectedIndividualOutDto3.setIsBusiness(false);
        expectedIndividualOutDto3.setName("firstName9 lastName9");
        expectedIndividualOutDto3.setObjId(9L);

        expectedIndividualOutDto3.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto3.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto3.setWebsite(null);

        expectedIndividualOutDtoList3.add(expectedIndividualOutDto3);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO3 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO3.setLast(true);

        expectedPagedIndividualOutDTO3.setUsers(expectedIndividualOutDtoList3);
        expectedPagedIndividualOutDTO3.setCount(3L);

        ResponseOutDTO responseOutDto3 = new ResponseOutDTO(true, expectedPagedIndividualOutDTO3);
        String expectedResult3 = objectMapper.writeValueAsString(responseOutDto3);

        ResponseOutDTO expectedResponseOutDto3 = gson.fromJson(expectedResult3, ResponseOutDTO.class);

        pgStart = 3;
        pgSize = 1;

        ResponseOutDTO actualResponseOutDTO3 = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult3 = objectMapper.writeValueAsString(actualResponseOutDTO3);
        ResponseOutDTO actualResponseOutDto3 = gson.fromJson(actualResult3, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto3, actualResponseOutDto3);

        List<IndividualOutDTO> expectedIndividualOutDtoList4 = null;

        PagedIndividualOutDTO expectedPagedIndividualOutDTO4 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO4.setLast(true);
        expectedPagedIndividualOutDTO4.setUsers(expectedIndividualOutDtoList4);
        expectedPagedIndividualOutDTO4.setCount(3L);

        ResponseOutDTO responseOutDto4 = new ResponseOutDTO(true, expectedPagedIndividualOutDTO4);
        String expectedResult4 = objectMapper.writeValueAsString(responseOutDto4);

        ResponseOutDTO expectedResponseOutDto4 = gson.fromJson(expectedResult4, ResponseOutDTO.class);

        pgStart = 4;
        pgSize = 1;

        ResponseOutDTO actualResponseOutDTO4 = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult4 = objectMapper.writeValueAsString(actualResponseOutDTO4);
        ResponseOutDTO actualResponseOutDto4 = gson.fromJson(actualResult4, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto4, actualResponseOutDto4);
    }

    //@Test
    public void testSearchIndividualUsersOnUK() throws Exception {
        String text = "";
        List<AccountStatus> accountStatusList = new ArrayList<>();
        String country = "UK";
        accountStatusList.add(AccountStatus.ACTIVE);
        accountStatusList.add(AccountStatus.INACTIVE);
        accountStatusList.add(AccountStatus.SUSPENDED);
        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);
        userTypeList.add(UserType.BUSINESS);

        SearchInDTO searchInDTO = setupSearchInDTO(text, accountStatusList, userTypeList);
        searchInDTO.setCountry(country);
        List<IndividualOutDTO> expectedIndividualOutDtoList = new ArrayList<>();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("addressline1");
        addressOutDTO.setAddressLine2("addressline2");
        addressOutDTO.setCity("city1");
        addressOutDTO.setCountry("UK");
        addressOutDTO.setPostalCode("111011");
        addressOutDTO.setState("state1");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();
        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName("bizName1");
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("test@email2.com");
        expectedIndividualOutDto.setIsBusiness(true);
        expectedIndividualOutDto.setName("firstName2 lastName2");
        expectedIndividualOutDto.setObjId(2L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.INACTIVE);
        expectedIndividualOutDto.setWebsite("testwebsite1");

        expectedIndividualOutDtoList.add(expectedIndividualOutDto);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO.setLast(true);
        expectedPagedIndividualOutDTO.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO.setCount(1L);
        ResponseOutDTO responseOutDto = new ResponseOutDTO(true, expectedPagedIndividualOutDTO);

        Gson gson = new Gson();
        String expectedResult = objectMapper.writeValueAsString(responseOutDto);
        ResponseOutDTO expectedResponseOutDto = gson.fromJson(expectedResult, ResponseOutDTO.class);

        String authToken = "authToken";

        pgStart = 1;
        pgSize = 10;

        ResponseOutDTO actualResponseOutDTO = webTestClient.post()
                .uri(uriBuilder -> uriBuilder.path("/admin/user/search").queryParam("pgStart", pgStart)
                        .queryParam("pgSize", pgSize).build())
                .body(BodyInserters.fromValue(searchInDTO)).header("authorization", authToken).exchange().expectStatus()
                .isOk().expectBody(ResponseOutDTO.class).returnResult().getResponseBody();

        String actualResult = objectMapper.writeValueAsString(actualResponseOutDTO);
        ResponseOutDTO actualResponseOutDto = gson.fromJson(actualResult, ResponseOutDTO.class);

        assertEquals(expectedResponseOutDto, actualResponseOutDto);
    }

    private SearchInDTO setupSearchInDTO(String text, List<AccountStatus> accountStatusList,
            List<UserType> userTypeList) {

        SearchInDTO searchInDTO = new SearchInDTO();

        searchInDTO.setText(text);
        searchInDTO.setUserStatus(accountStatusList);
        searchInDTO.setUserType(userTypeList);

        return searchInDTO;
    }

    private List<IndividualOutDTO> setExpectedData() {

        List<IndividualOutDTO> individualOutDTOList = new ArrayList<>();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("addressline1");
        addressOutDTO.setAddressLine2("addressline2");
        addressOutDTO.setCity("city1");
        addressOutDTO.setCountry("US");
        addressOutDTO.setPostalCode("111011");
        addressOutDTO.setState("state1");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();
        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName(null);
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("test@email1.com");
        expectedIndividualOutDto.setIsBusiness(false);
        expectedIndividualOutDto.setName("firstName1 lastName1");
        expectedIndividualOutDto.setObjId(1L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite(null);

        individualOutDTOList.add(expectedIndividualOutDto);

        return individualOutDTOList;
    }
}
