package us.fyndr.api.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import us.fyndr.api.admin.dto.CampaignDetailsInDTO;
import us.fyndr.api.admin.dto.CampaignDetailsOutDTO;
import us.fyndr.api.admin.dto.PagedCampaignDetailOutDTO;
import us.fyndr.api.admin.model.CampaignDetails;
import us.fyndr.api.admin.model.CountCampaignOffers;
import us.fyndr.api.admin.repository.CampaignDetailsRepository;
import us.fyndr.api.admin.repository.CampaignOfferDetailsRepository;
import us.fyndr.api.admin.repository.CountryRepository;
import us.fyndr.api.admin.repository.IndividualRepository;

public class CampaignDetailsServiceTest {

    @InjectMocks
    private CampaignDetailsService campaignDetailsService;

    @Mock
    private CampaignDetailsRepository campaignDetailsRepository;

    @Mock
    private IndividualRepository individualRepository;

    @Mock
    private CountryRepository countryRepository;

    @Mock
    private CampaignOfferDetailsRepository getCampaignOfferDetailsRepository;

    private CountCampaignOffers countOffers = new CountCampaignOffers(12, 16, 650);

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    private Instant currentTimeStamp = Instant.now();
    private LocalDate endDate = currentTimeStamp.atZone(ZoneId.of("UTC")).toLocalDate();

    @Test
    public void testSearchCampaignDetails() {
        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();
        campaignDetailsInDTO.setBusinessName("abc");
        campaignDetailsInDTO.setCountry("US");
        campaignDetailsInDTO.setStartDate(LocalDate.of(2022, 01, 1));
        campaignDetailsInDTO.setEndDate(LocalDate.of(2022, 10, 10));

        Integer cmpnId = 1;
        String buisnessName = "buisnessName";
        String campaignName = "campaignName";
        String buisnessType = "industryType";
        String campaignType = "campaignType";
        Double totalOfferSoldAmount = 12.23;
        Instant endDt = currentTimeStamp;
        String currency = "USD";
        String currencySymbol = "$";

        CampaignDetails campaignDetailDTO = new CampaignDetails(cmpnId, buisnessName, campaignName, buisnessType,
                campaignType, totalOfferSoldAmount, endDt);

        List<CampaignDetails> campaignList = new ArrayList<>();
        campaignList.add(campaignDetailDTO);

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();
        campaignDetailsOutDTO.setActiveOffers(countOffers.getActiveOffer());
        campaignDetailsOutDTO.setBusinessName(campaignDetailDTO.getBuisnessName());
        campaignDetailsOutDTO.setCampaignName(campaignDetailDTO.getCampaignName());
        campaignDetailsOutDTO.setCampaignType(campaignDetailDTO.getCampaignType());
        campaignDetailsOutDTO.setEndDate(endDate);
        campaignDetailsOutDTO.setIndustryType("industryType");
        campaignDetailsOutDTO.setOfferSold(countOffers.getOfferSold());
        campaignDetailsOutDTO.setTotalOffers(countOffers.getTotalOffer());
        campaignDetailsOutDTO.setTotalOfferSoldAmount(12.23);
        campaignDetailsOutDTO.setCurrency(currency);
        campaignDetailsOutDTO.setCurrencySymbol(currencySymbol);
        campaignDetailsOutDTO.setObjId(1l);

        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        PagedCampaignDetailOutDTO mockPagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();
        mockPagedCampaignDetailOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        mockPagedCampaignDetailOutDTO.setLast(true);
        mockPagedCampaignDetailOutDTO.setCount(1l);

        final int pgStart = 0;
        final int pgSize = 10;

        String whereConditions = " AND ( im.country = 'US' )  AND ( bm.biz_name ILIKE '%abc%' )  AND ( c.end_dt BETWEEN '2022-01-01' AND '2022-10-10') ";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);

        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);

        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO);

        assertEquals(mockPagedCampaignDetailOutDTO, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testCampaignListIsNullCriteria() {

        String businessName = new String();
        String country = new String();
        LocalDate endDate = null;
        LocalDate startDate = null;

        CampaignDetailsInDTO campaignDetailsInDTO1 = setupData(businessName, country, endDate, startDate);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTO2 = null;
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2.setLast(true);
        pagedCampaignDetailOutDTO2.setCampaignDetails(campaignDetailsOutDTO2);
        pagedCampaignDetailOutDTO2.setCount(1l);

        List<CampaignDetails> campaignList = CampaignDetailsData();
        int pgStart = 0;
        int pgSize = 10;

        String whereConditions = "";

        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(null);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO1);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        assertEquals(pagedCampaignDetailOutDTO2, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testBusinessNameSearchCriteria() {
        String businessName = "abc";
        String country = new String();
        LocalDate endDate = null;
        LocalDate startDate = null;
        CampaignDetailsInDTO campaignDetailsInDTO1 = setupData(businessName, country, endDate, startDate);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTO2 = setExpectedData();
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2.setLast(true);
        pagedCampaignDetailOutDTO2.setCampaignDetails(campaignDetailsOutDTO2);
        pagedCampaignDetailOutDTO2.setCount(1l);
        List<CampaignDetails> campaignList = CampaignDetailsData();
        int pgStart = 0;
        int pgSize = 10;
        String whereConditions = " AND ( bm.biz_name ILIKE '%abc%' ) ";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO1);
        assertEquals(pagedCampaignDetailOutDTO2, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testDateSearchCriteria() {
        String businessName = null;
        String country = null;
        LocalDate startDate = LocalDate.of(2022, 01, 1);
        LocalDate endDate = LocalDate.of(2022, 10, 10);

        CampaignDetailsInDTO campaignDetailsInDTO1 = setupData(businessName, country, endDate, startDate);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTO2 = setExpectedData();
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2.setLast(true);
        pagedCampaignDetailOutDTO2.setCampaignDetails(campaignDetailsOutDTO2);
        pagedCampaignDetailOutDTO2.setCount(1l);
        List<CampaignDetails> campaignList = CampaignDetailsData();
        int pgStart = 0;
        int pgSize = 10;
        String whereConditions = " AND ( c.end_dt BETWEEN '2022-10-10' AND '2022-01-01') ";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO1);
        assertEquals(pagedCampaignDetailOutDTO2, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testCountrySearchCriteria() {
        String businessName = new String();
        String country = "US";
        LocalDate endDate = null;
        LocalDate startDate = null;
        CampaignDetailsInDTO campaignDetailsInDTO1 = setupData(businessName, country, endDate, startDate);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTO2 = setExpectedData();
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2.setLast(true);
        pagedCampaignDetailOutDTO2.setCampaignDetails(campaignDetailsOutDTO2);
        pagedCampaignDetailOutDTO2.setCount(1l);
        List<CampaignDetails> campaignList = CampaignDetailsData();
        int pgStart = 0;
        int pgSize = 10;
        String whereConditions = " AND ( im.country = 'US' ) ";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO1);
        assertEquals(pagedCampaignDetailOutDTO2, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testEndDateNUllCriteria() {

        LocalDate startDate = LocalDate.of(2022, 01, 1);
        LocalDate endDate = null;
        CampaignDetailsInDTO campaignDetailsInDTO1 = setupData(null, null, endDate, startDate);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTO2 = setExpectedData();
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2.setLast(true);
        pagedCampaignDetailOutDTO2.setCampaignDetails(campaignDetailsOutDTO2);
        pagedCampaignDetailOutDTO2.setCount(1l);
        List<CampaignDetails> campaignList = CampaignDetailsData();
        int pgStart = 0;
        int pgSize = 10;
        String whereConditions = "";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO1);
        assertEquals(pagedCampaignDetailOutDTO2, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testStartDateNUllCriteria() {

        LocalDate endDate = LocalDate.of(2022, 01, 1);
        LocalDate startDate = null;
        CampaignDetailsInDTO campaignDetailsInDTO1 = setupData(null, null, endDate, startDate);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTO2 = setExpectedData();
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2.setLast(true);
        pagedCampaignDetailOutDTO2.setCampaignDetails(campaignDetailsOutDTO2);
        pagedCampaignDetailOutDTO2.setCount(1l);
        List<CampaignDetails> campaignList = CampaignDetailsData();
        int pgStart = 0;
        int pgSize = 10;
        String whereConditions = "";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO1);
        assertEquals(pagedCampaignDetailOutDTO2, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testSBothDateNUllCriteria() {

        LocalDate endDate = null;
        LocalDate startDate = null;
        CampaignDetailsInDTO campaignDetailsInDTO1 = setupData(null, null, endDate, startDate);
        List<CampaignDetailsOutDTO> campaignDetailsOutDTO2 = setExpectedData();
        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2.setLast(true);
        pagedCampaignDetailOutDTO2.setCampaignDetails(campaignDetailsOutDTO2);
        pagedCampaignDetailOutDTO2.setCount(1l);
        List<CampaignDetails> campaignList = CampaignDetailsData();
        int pgStart = 0;
        int pgSize = 10;
        String whereConditions = "";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO1);
        assertEquals(pagedCampaignDetailOutDTO2, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testNullValues() {

        String businessName = null;
        String country = null;
        LocalDate startDate = null;
        LocalDate endDate = null;

        CampaignDetailsInDTO campaignDetailsInDTO1 = setupData(businessName, country, startDate, endDate);

        List<CampaignDetailsOutDTO> campaignDetailsOutDTO2 = setExpectedData();

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO2 = new PagedCampaignDetailOutDTO();
        pagedCampaignDetailOutDTO2.setLast(true);
        pagedCampaignDetailOutDTO2.setCampaignDetails(campaignDetailsOutDTO2);
        pagedCampaignDetailOutDTO2.setCount(1L);

        List<CampaignDetails> campaignList = CampaignDetailsData();
        int pgStart = 0;
        int pgSize = 10;

        String whereConditions = "";

        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO1);

        assertEquals(pagedCampaignDetailOutDTO2, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testDifferentPageScenarios() {

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();
        campaignDetailsInDTO.setBusinessName("abc");
        campaignDetailsInDTO.setCountry("US");

        Integer cmpnId = 1;
        String buisnessName = "buisnessName";
        String campaignName = "campaignName";
        String buisnessType = "industryType";
        String campaignType = "campaignType";
        Double totalOfferSoldAmount = 12.23;
        Instant endDt = currentTimeStamp;
        String currency = "USD";
        String currencySymbol = "$";

        CampaignDetails campaignDetailDTO = new CampaignDetails(cmpnId, buisnessName, campaignName, buisnessType,
                campaignType, totalOfferSoldAmount, endDt);

        List<CampaignDetails> campaignList = new ArrayList<>();
        campaignList.add(campaignDetailDTO);

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();
        campaignDetailsOutDTO.setActiveOffers(countOffers.getActiveOffer());
        campaignDetailsOutDTO.setBusinessName(campaignDetailDTO.getBuisnessName());
        campaignDetailsOutDTO.setCampaignName(campaignDetailDTO.getCampaignName());
        campaignDetailsOutDTO.setCampaignType(campaignDetailDTO.getCampaignType());
        campaignDetailsOutDTO.setEndDate(endDate);
        campaignDetailsOutDTO.setIndustryType("industryType");
        campaignDetailsOutDTO.setOfferSold(countOffers.getOfferSold());
        campaignDetailsOutDTO.setTotalOffers(countOffers.getTotalOffer());
        campaignDetailsOutDTO.setTotalOfferSoldAmount(12.23);
        campaignDetailsOutDTO.setCurrency(currency);
        campaignDetailsOutDTO.setCurrencySymbol(currencySymbol);
        campaignDetailsOutDTO.setObjId(1l);

        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        PagedCampaignDetailOutDTO mockPagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();
        mockPagedCampaignDetailOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        mockPagedCampaignDetailOutDTO.setLast(true);
        mockPagedCampaignDetailOutDTO.setCount(1l);

        final int pgStart = 0;
        final int pgSize = 10;

        String whereConditions = " AND ( im.country = 'US' )  AND ( bm.biz_name ILIKE '%abc%' ) ";
        int expectedSize = 1;
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);

        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO);

        assertEquals(expectedSize, pagedCampaignDetailOutDTO.getCampaignDetails().size());
    }

    @Test
    public void testEmptySearchCriteria() {

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();

        Integer cmpnId = 1;
        String buisnessName = "buisnessName";
        String campaignName = "campaignName";
        String buisnessType = "industryType";
        String campaignType = "campaignType";
        Double totalOfferSoldAmount = null;
        Instant endDt = currentTimeStamp;
        String currency = "USD";
        String currencySymbol = "$";

        CampaignDetails campaignDetailDTO = new CampaignDetails(cmpnId, buisnessName, campaignName, buisnessType,
                campaignType, totalOfferSoldAmount, endDt);

        List<CampaignDetails> campaignList = new ArrayList<>();
        campaignList.add(campaignDetailDTO);

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();
        campaignDetailsOutDTO.setActiveOffers(countOffers.getActiveOffer());
        campaignDetailsOutDTO.setBusinessName(campaignDetailDTO.getBuisnessName());
        campaignDetailsOutDTO.setCampaignName(campaignDetailDTO.getCampaignName());
        campaignDetailsOutDTO.setCampaignType(campaignDetailDTO.getCampaignType());
        campaignDetailsOutDTO.setEndDate(endDate);
        campaignDetailsOutDTO.setIndustryType("industryType");
        campaignDetailsOutDTO.setOfferSold(countOffers.getOfferSold());
        campaignDetailsOutDTO.setTotalOffers(countOffers.getTotalOffer());
        campaignDetailsOutDTO.setTotalOfferSoldAmount(0.0);
        campaignDetailsOutDTO.setCurrency(currency);
        campaignDetailsOutDTO.setCurrencySymbol(currencySymbol);
        campaignDetailsOutDTO.setObjId(1l);

        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        PagedCampaignDetailOutDTO mockPagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();
        mockPagedCampaignDetailOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        mockPagedCampaignDetailOutDTO.setLast(false);
        mockPagedCampaignDetailOutDTO.setCount(1l);

        final int pgStart = 10;
        final int pgSize = 0;

        String whereConditions = "";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);

        Mockito.when(getCampaignOfferDetailsRepository.getCampaignOfferDetails(campaignList.get(0).getCampaignId()))
                .thenReturn(countOffers);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO);

        assertEquals(mockPagedCampaignDetailOutDTO, pagedCampaignDetailOutDTO);
    }

    @Test
    public void testWhenCampaignIdNull() {

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();
        campaignDetailsInDTO.setBusinessName("abc");
        campaignDetailsInDTO.setCountry("US");

        Integer cmpnId = null;
        String buisnessName = "buisnessName";
        String campaignName = "campaignName";
        String buisnessType = "industryType";
        String campaignType = "campaignType";
        Double totalOfferSoldAmount = 12.23;
        Instant endDt = currentTimeStamp;
        String currency = "USD";
        String currencySymbol = "$";

        CampaignDetails campaignDetailDTO = new CampaignDetails(cmpnId, buisnessName, campaignName, buisnessType,
                campaignType, totalOfferSoldAmount, endDt);

        List<CampaignDetails> campaignList = new ArrayList<>();
        campaignList.add(campaignDetailDTO);

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();
        campaignDetailsOutDTO.setActiveOffers(null);
        campaignDetailsOutDTO.setBusinessName(campaignDetailDTO.getBuisnessName());
        campaignDetailsOutDTO.setCampaignName(campaignDetailDTO.getCampaignName());
        campaignDetailsOutDTO.setCampaignType(campaignDetailDTO.getCampaignType());
        campaignDetailsOutDTO.setEndDate(endDate);
        campaignDetailsOutDTO.setIndustryType("industryType");
        campaignDetailsOutDTO.setOfferSold(null);
        campaignDetailsOutDTO.setTotalOffers(null);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(12.23);
        campaignDetailsOutDTO.setCurrency(currency);
        campaignDetailsOutDTO.setCurrencySymbol(currencySymbol);
        campaignDetailsOutDTO.setObjId(1l);

        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        PagedCampaignDetailOutDTO mockPagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();
        mockPagedCampaignDetailOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        mockPagedCampaignDetailOutDTO.setLast(true);
        mockPagedCampaignDetailOutDTO.setCount(1l);

        final int pgStart = 0;
        final int pgSize = 10;

        String whereConditions = " AND ( im.country = 'US' )  AND ( bm.biz_name ILIKE '%abc%' ) ";
        Mockito.when(campaignDetailsRepository.getCampaignDetailByFiltersCriteria(whereConditions, pgStart, pgSize))
                .thenReturn(campaignList);
        Mockito.when(campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions)).thenReturn(1);
        Mockito.when(individualRepository.fetchObjIdByBusinessName(campaignList.get(0).getBuisnessName()))
                .thenReturn(1l);
        Mockito.when(countryRepository.getCurrencyByIndividualId(1l)).thenReturn("USD");
        Mockito.when(countryRepository.getCurrencySymbolByIndividualId(1l)).thenReturn("$");

        PagedCampaignDetailOutDTO pagedCampaignDetailOutDTO = campaignDetailsService.getCampaignDetails(pgStart, pgSize,
                campaignDetailsInDTO);
        assertEquals(mockPagedCampaignDetailOutDTO, pagedCampaignDetailOutDTO);
    }

    private List<CampaignDetails> CampaignDetailsData() {
        Integer cmpnId = 1;
        String buisnessName = "buisnessName";
        String campaignName = "campaignName";
        String buisnessType = "industryType";
        String campaignType = "campaignType";
        Double totalOfferSoldAmount = 12.23;
        Instant endDt = currentTimeStamp;

        CampaignDetails campaignDetailDTO = new CampaignDetails(cmpnId, buisnessName, campaignName, buisnessType,
                campaignType, totalOfferSoldAmount, endDt);

        List<CampaignDetails> campaignList = new ArrayList<>();
        campaignList.add(campaignDetailDTO);
        return campaignList;
    }

    private CampaignDetailsInDTO setupData(String businessName, String country, LocalDate startDate,
            LocalDate endDate) {

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();

        campaignDetailsInDTO.setBusinessName(businessName);
        campaignDetailsInDTO.setCountry(country);
        campaignDetailsInDTO.setEndDate(endDate);
        campaignDetailsInDTO.setStartDate(startDate);

        return campaignDetailsInDTO;
    }

    private List<CampaignDetailsOutDTO> setExpectedData() {

        Integer cmpnId = 1;
        String buisnessName = "buisnessName";
        String campaignName = "campaignName";
        String buisnessType = "industryType";
        String campaignType = "campaignType";
        Double totalOfferSoldAmount = 12.23;
        Instant endDt = currentTimeStamp;
        String currency = "USD";
        String currencySymbol = "$";

        CampaignDetails campaignDetailDTO = new CampaignDetails(cmpnId, buisnessName, campaignName, buisnessType,
                campaignType, totalOfferSoldAmount, endDt);

        List<CampaignDetails> campaignList = new ArrayList<>();
        campaignList.add(campaignDetailDTO);

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName(campaignDetailDTO.getBuisnessName());
        campaignDetailsOutDTO.setCampaignName(campaignDetailDTO.getCampaignName());
        campaignDetailsOutDTO.setCampaignType(campaignDetailDTO.getCampaignType());
        campaignDetailsOutDTO.setEndDate(endDate);
        campaignDetailsOutDTO.setIndustryType("industryType");
        campaignDetailsOutDTO.setOfferSold(countOffers.getOfferSold());
        campaignDetailsOutDTO.setActiveOffers(countOffers.getActiveOffer());
        campaignDetailsOutDTO.setTotalOffers(countOffers.getTotalOffer());
        campaignDetailsOutDTO.setTotalOfferSoldAmount(totalOfferSoldAmount);
        campaignDetailsOutDTO.setCurrency(currency);
        campaignDetailsOutDTO.setCurrencySymbol(currencySymbol);
        campaignDetailsOutDTO.setObjId(1l);

        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<>();
        campaignDetailsOutDTOList.add(campaignDetailsOutDTO);

        return campaignDetailsOutDTOList;
    }

}
