package us.fyndr.api.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.Business;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dbo.UserType;
import us.fyndr.api.admin.dto.AddressOutDTO;
import us.fyndr.api.admin.dto.IndividualOutDTO;
import us.fyndr.api.admin.dto.PagedIndividualOutDTO;
import us.fyndr.api.admin.dto.PhoneNumberOutDTO;
import us.fyndr.api.admin.dto.SearchInDTO;
import us.fyndr.api.admin.repository.BusinessRepository;
import us.fyndr.api.admin.repository.IndividualSearchRepository;

public class SearchServiceTest {

    @InjectMocks
    private SearchService searchService;

    @Mock
    private IndividualSearchRepository individualSearchRepository;

    @Mock
    private BusinessRepository businessRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Instant currentTimeStamp = Instant.now();

    @Test
    public void testSearchBusinessActiveUsers() {

        String text = "User";
        List<AccountStatus> accountStatusList = new ArrayList<>();
        accountStatusList.add(AccountStatus.ACTIVE);

        List<UserType> userTypeList = new ArrayList<>();
        userTypeList.add(UserType.BUSINESS);

        String country = "US";

        SearchInDTO searchInDTO = setupData(text, accountStatusList, userTypeList, country);

        List<IndividualOutDTO> expectedIndividualOutDtoList = new ArrayList<>();

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();

        AddressOutDTO addressOutDTO = new AddressOutDTO();
        addressOutDTO.setAddressLine1("Test Address1");
        addressOutDTO.setAddressLine2("Test Address2");
        addressOutDTO.setCity("Test City");
        addressOutDTO.setCountry("US");
        addressOutDTO.setPostalCode("999999");
        addressOutDTO.setState("Test State");

        expectedIndividualOutDto.setAddress(addressOutDTO);

        expectedIndividualOutDto.setBusinessName("Test Business");
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("Test@gmail.com");
        expectedIndividualOutDto.setIsBusiness(true);
        expectedIndividualOutDto.setName("Test user");
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite("Test.com");
        expectedIndividualOutDto.setObjId(73L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");
        phoneNumberOutDTO.setPhoneNumber("999999999");

        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);


        expectedIndividualOutDtoList.add(expectedIndividualOutDto);

        List<Individual> individualList = new ArrayList<>();
        Individual individual = new Individual();
        individual.setBusinessId(1000149L);
        individual.setAccountStatus(AccountStatus.ACTIVE);
        individual.setAddressLine1("Test Address1");
        individual.setAddressLine2("Test Address2");
        individual.setCity("Test City");
        individual.setCountry("US");
        individual.setCountryCode("+91");
        individual.setCreatedDt(currentTimeStamp);
        individual.setEmail("Test@gmail.com");
        individual.setFirstName("Test");
        individual.setIsBusiness(true);
        individual.setLastName("user");
        individual.setObjId(73L);
        individual.setPhone("999999999");
        individual.setPostalCode("999999");
        individual.setState("Test State");

        individualList.add(individual);

        int pgStart = 0;
        int pgSize = 10;

        Business businessDBO = new Business();
        String businessName = "Test Business";
        businessDBO.setBusinessName(businessName);
        long objid = 12;
        businessDBO.setObjId(objid);
        String website = "Test.com";
        businessDBO.setWebsite(website);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO1.setLast(true);
        expectedPagedIndividualOutDTO1.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO1.setCount(1L);

        String whereConditions = " AND im.account_status IN ('ACTIVE') AND im.is_business in ('true') AND (( im.first_name ILIKE '%User%' ) OR ( im.last_name ILIKE '%User%' ) OR ( bm.biz_name ILIKE '%User%' )) AND ( im.country = 'US' )";
        Mockito.when(individualSearchRepository.searchByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(individualList);
        Mockito.when(individualSearchRepository.countSearchByFiltersCriteria(whereConditions))
                .thenReturn(1);
        Mockito.when(businessRepository.findById(individualList.get(0).getBusinessId()))
                .thenReturn(Optional.of(businessDBO));
        PagedIndividualOutDTO pagedIndividualOutDTO1 = searchService.searchIndividualUsers(pgStart, pgSize,
                searchInDTO);

        assertEquals(expectedPagedIndividualOutDTO1, pagedIndividualOutDTO1);
    }

    @Test
    public void testSearchAllUsersActiveAndInactive() {

        String text = "User";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);
        accountStatusList.add(AccountStatus.INACTIVE);

        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);
        userTypeList.add(UserType.BUSINESS);

        String country = "US";

        SearchInDTO searchInDTO = setupData(text, accountStatusList, userTypeList, country);

        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        PagedIndividualOutDTO expectedPagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO1.setLast(true);
        expectedPagedIndividualOutDTO1.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO1.setCount(1L);

        List<Individual> list = individualData();
        int pgStart = 0;
        int pgSize = 10;

        String whereConditions = " AND im.account_status IN ('ACTIVE','INACTIVE') AND im.is_business in ('false','true') AND (( im.first_name ILIKE '%User%' ) OR ( im.last_name ILIKE '%User%' ) OR ( bm.biz_name ILIKE '%User%' )) AND ( im.country = 'US' )";

        Mockito.when(individualSearchRepository.searchByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(list);
        Mockito.when(individualSearchRepository.countSearchByFiltersCriteria(whereConditions))
                .thenReturn(1);

        PagedIndividualOutDTO pagedIndividualOutDTO = searchService.searchIndividualUsers(pgStart, pgSize, searchInDTO);

        assertEquals(expectedPagedIndividualOutDTO1, pagedIndividualOutDTO);
    }

    @Test
    public void testNullValuesforSlice() {

        String text = "jfhrhu";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.SUSPENDED);

        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);

        String country = "jfd";

        SearchInDTO searchInDTO = setupData(text, accountStatusList, userTypeList, country);

        PagedIndividualOutDTO expectedPagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO1.setLast(true);
        expectedPagedIndividualOutDTO1.setUsers(null);
        expectedPagedIndividualOutDTO1.setCount(0L);

        int pgStart = 0;
        int pgSize = 10;

        String whereConditions = " AND im.account_status IN ('SUSPENDED') AND im.is_business in ('false') AND ('jfhrhu' OPERATOR({h-schema}%) ANY(STRING_TO_ARRAY(im.first_name ,' ')) OR 'jfhrhu' OPERATOR({h-schema}%) ANY(STRING_TO_ARRAY(im.last_name ,' ')) OR 'jfhrhu' OPERATOR({h-schema}%) ANY(STRING_TO_ARRAY(im.email ,' ')) OR 'jfhrhu' OPERATOR({h-schema}%) ANY(STRING_TO_ARRAY(bm.biz_name ,' ')) OR 'jfhrhu' OPERATOR({h-schema}%) ANY(STRING_TO_ARRAY(bm.website ,' '))) AND ( im.country = 'jfd' )";

        Mockito.when(individualSearchRepository.searchByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(null);
        Mockito.when(individualSearchRepository.countSearchByFiltersCriteria(whereConditions)).thenReturn(0);

        PagedIndividualOutDTO pagedIndividualOutDTO = searchService.searchIndividualUsers(pgStart, pgSize, searchInDTO);

        assertEquals(expectedPagedIndividualOutDTO1, pagedIndividualOutDTO);
    }

    @Test
    public void testSearchAllUsersActiveInactiveAndSuspended() {

        String text = "User";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);
        accountStatusList.add(AccountStatus.INACTIVE);
        accountStatusList.add(AccountStatus.SUSPENDED);

        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);
        userTypeList.add(UserType.BUSINESS);

        String country = "US";

        SearchInDTO searchInDTO = setupData(text, accountStatusList, userTypeList, country);

        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        PagedIndividualOutDTO expectedPagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO1.setLast(true);
        expectedPagedIndividualOutDTO1.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO1.setCount(1L);

        List<Individual> list = individualData();
        int pgStart = 0;
        int pgSize = 10;

        String whereConditions = " AND im.account_status IN ('ACTIVE','INACTIVE','SUSPENDED') AND im.is_business in ('false','true') AND (( im.first_name ILIKE '%User%' ) OR ( im.last_name ILIKE '%User%' ) OR ( bm.biz_name ILIKE '%User%' )) AND ( im.country = 'US' )";

        Mockito.when(individualSearchRepository.searchByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(list);
        Mockito.when(individualSearchRepository.countSearchByFiltersCriteria(whereConditions))
                .thenReturn(1);
        PagedIndividualOutDTO pagedIndividualOutDTO = searchService.searchIndividualUsers(pgStart, pgSize, searchInDTO);

        assertEquals(expectedPagedIndividualOutDTO1, pagedIndividualOutDTO);
    }

    @Test
    public void testSearchAllUsersActiveInactiveSuspendedAndDeleted() {

        String text = "User";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);
        accountStatusList.add(AccountStatus.INACTIVE);
        accountStatusList.add(AccountStatus.SUSPENDED);
        accountStatusList.add(AccountStatus.DELETED);

        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);
        userTypeList.add(UserType.BUSINESS);

        String country = "US";

        SearchInDTO searchInDTO = setupData(text, accountStatusList, userTypeList, country);

        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        PagedIndividualOutDTO expectedPagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO1.setLast(true);
        expectedPagedIndividualOutDTO1.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO1.setCount(1L);

        List<Individual> list = individualData();
        int pgStart = 0;
        int pgSize = 10;

        String whereConditions = " AND im.account_status IN ('ACTIVE','INACTIVE','SUSPENDED','DELETED') AND im.is_business in ('false','true') AND (( im.first_name ILIKE '%User%' ) OR ( im.last_name ILIKE '%User%' ) OR ( bm.biz_name ILIKE '%User%' )) AND ( im.country = 'US' )";

        Mockito.when(individualSearchRepository.searchByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(list);
        Mockito.when(individualSearchRepository.countSearchByFiltersCriteria(whereConditions))
                .thenReturn(1);
        PagedIndividualOutDTO pagedIndividualOutDTO = searchService.searchIndividualUsers(pgStart, pgSize, searchInDTO);

        assertEquals(expectedPagedIndividualOutDTO1, pagedIndividualOutDTO);
    }

    @Test
    public void testDifferentPageScenarios() {

        String text = "User";
        List<AccountStatus> accountStatusList = new ArrayList<>();

        accountStatusList.add(AccountStatus.ACTIVE);
        accountStatusList.add(AccountStatus.INACTIVE);
        accountStatusList.add(AccountStatus.SUSPENDED);
        accountStatusList.add(AccountStatus.DELETED);

        List<UserType> userTypeList = new ArrayList<>();

        userTypeList.add(UserType.INDIVIDUAL);
        userTypeList.add(UserType.BUSINESS);

        String country = "US";

        SearchInDTO searchInDTO = setupData(text, accountStatusList, userTypeList, country);

        Business businessDBO = new Business();
        String businessName = "Test Business";
        businessDBO.setBusinessName(businessName);
        long objid = 12;
        businessDBO.setObjId(objid);
        String website = "Test.com";
        businessDBO.setWebsite(website);

        List<Individual> dbIndividualList = individualData();
        Individual individual = new Individual();

        individual.setObjId(72L);
        individual.setQrid(500L);
        individual.setFirstName("Test");
        individual.setLastName("User 1");
        individual.setYob("2022");
        individual.setFyndrHandle("testUser2022");
        individual.setPhone("8888888888");
        individual.setCountryCode("+1");
        individual.setEmail("testUser2022@gmail.com");
        individual.setCreatedDt(currentTimeStamp);
        individual.setAddressLine1("Address Line 1");
        individual.setAddressLine2("Address Line 2");
        individual.setCity("City 1");
        individual.setCountry("Country");
        individual.setPostalCode("67568");
        individual.setIsBusiness(false);
        individual.setAccountStatus(AccountStatus.ACTIVE);
        dbIndividualList.add(individual);

        List<Individual> expectedIndividualList = new ArrayList<>();
        expectedIndividualList.add(dbIndividualList.get(0));

        int pgStart = 0;
        int pgSize = 1;

        String whereConditions = " AND im.account_status IN ('ACTIVE','INACTIVE','SUSPENDED','DELETED') AND im.is_business in ('false','true') AND (( im.first_name ILIKE '%User%' ) OR ( im.last_name ILIKE '%User%' ) OR ( bm.biz_name ILIKE '%User%' )) AND ( im.country = 'US' )";
        int expectedSize = 1;
        Mockito.when(individualSearchRepository.searchByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(expectedIndividualList);
        Mockito.when(individualSearchRepository.countSearchByFiltersCriteria(whereConditions))
        .thenReturn(3);

        Mockito.when(businessRepository.findById(dbIndividualList.get(0).getBusinessId()))
                .thenReturn(Optional.of(businessDBO));

        PagedIndividualOutDTO pagedIndividualOutDTO = searchService.searchIndividualUsers(pgStart, pgSize, searchInDTO);

        assertEquals(expectedSize, pagedIndividualOutDTO.getUsers().size());
    }

    @Test
    public void testWithoutAnySearchCriteria() {

        String text = new String();
        List<AccountStatus> accountStatusList = new ArrayList<>();

        List<UserType> userTypeList = new ArrayList<>();

        String country = new String();

        SearchInDTO searchInDTO = setupData(text, accountStatusList, userTypeList, country);
        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        PagedIndividualOutDTO expectedPagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO1.setLast(true);
        expectedPagedIndividualOutDTO1.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO1.setCount(1L);

        List<Individual> list = individualData();
        int pgStart = 0;
        int pgSize = 10;

        String whereConditions = "";

        Mockito.when(individualSearchRepository.searchByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(list);
        Mockito.when(individualSearchRepository.countSearchByFiltersCriteria(whereConditions))
                .thenReturn(1);
        PagedIndividualOutDTO pagedIndividualOutDTO = searchService.searchIndividualUsers(pgStart, pgSize, searchInDTO);

        assertEquals(expectedPagedIndividualOutDTO1, pagedIndividualOutDTO);
    }

    @Test
    public void testNullValues() {

        String text = null;
        List<AccountStatus> accountStatusList = null;
        List<UserType> userTypeList = null;
        String country = null;

        SearchInDTO searchInDTO = setupData(text, accountStatusList, userTypeList, country);

        List<IndividualOutDTO> expectedIndividualOutDtoList = setExpectedData();

        PagedIndividualOutDTO expectedPagedIndividualOutDTO1 = new PagedIndividualOutDTO();
        expectedPagedIndividualOutDTO1.setLast(true);
        expectedPagedIndividualOutDTO1.setUsers(expectedIndividualOutDtoList);
        expectedPagedIndividualOutDTO1.setCount(1L);

        List<Individual> list = individualData();
        int pgStart = 0;
        int pgSize = 10;

        String whereConditions = "";

        Mockito.when(individualSearchRepository.searchByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(list);
        Mockito.when(individualSearchRepository.countSearchByFiltersCriteria(whereConditions))
                .thenReturn(1);
        PagedIndividualOutDTO pagedIndividualOutDTO = searchService.searchIndividualUsers(pgStart, pgSize, searchInDTO);

        assertEquals(expectedPagedIndividualOutDTO1, pagedIndividualOutDTO);
    }

    private SearchInDTO setupData(String text, List<AccountStatus> accountStatusList, List<UserType> userTypeList,
            String country) {

        SearchInDTO searchInDTO = new SearchInDTO();

        searchInDTO.setText(text);
        searchInDTO.setUserStatus(accountStatusList);
        searchInDTO.setUserType(userTypeList);
        searchInDTO.setCountry(country);

        return searchInDTO;
    }

    private List<IndividualOutDTO> setExpectedData() {

        List<IndividualOutDTO> individualOutDTOList = new ArrayList<>();

        AddressOutDTO addressOutDTO = new AddressOutDTO();

        addressOutDTO.setAddressLine1("Test Address1");
        addressOutDTO.setAddressLine2("Test Address2");
        addressOutDTO.setCity("Test City");
        addressOutDTO.setCountry("US");
        addressOutDTO.setPostalCode("999999");
        addressOutDTO.setState("Test State");

        IndividualOutDTO expectedIndividualOutDto = new IndividualOutDTO();
        expectedIndividualOutDto.setAddress(addressOutDTO);
        expectedIndividualOutDto.setBusinessName(null);
        expectedIndividualOutDto.setCreateDt(currentTimeStamp);
        expectedIndividualOutDto.setEmail("Test@gmail.com");
        expectedIndividualOutDto.setIsBusiness(false);
        expectedIndividualOutDto.setName("Test user");
        expectedIndividualOutDto.setObjId(71L);

        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+1");
        phoneNumberOutDTO.setPhoneNumber("999999999");
        expectedIndividualOutDto.setPhone(phoneNumberOutDTO);
        expectedIndividualOutDto.setStatus(AccountStatus.ACTIVE);
        expectedIndividualOutDto.setWebsite(null);

        individualOutDTOList.add(expectedIndividualOutDto);

        return individualOutDTOList;
    }

    private List<Individual> individualData() {

        List<Individual> individualList = new ArrayList<>();
        Individual individual = new Individual();
        individual.setBusinessId(1000149L);
        individual.setAccountStatus(AccountStatus.ACTIVE);
        individual.setAddressLine1("Test Address1");
        individual.setAddressLine2("Test Address2");
        individual.setCity("Test City");
        individual.setCountry("US");
        individual.setCountryCode("+1");
        individual.setCreatedDt(currentTimeStamp);
        individual.setEmail("Test@gmail.com");
        individual.setFirstName("Test");
        individual.setIsBusiness(false);
        individual.setLastName("user");
        individual.setObjId(71L);
        individual.setPhone("999999999");
        individual.setPostalCode("999999");
        individual.setState("Test State");

        individualList.add(individual);

        return individualList;
    }
}
