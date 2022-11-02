package us.fyndr.api.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import us.fyndr.api.admin.dto.PagedRevenueDetailsOutDTO;
import us.fyndr.api.admin.dto.RevenueDetailsInDTO;
import us.fyndr.api.admin.dto.RevenueDetailsOutDTO;
import us.fyndr.api.admin.model.RevenueDetails;
import us.fyndr.api.admin.repository.IndividualRepository;
import us.fyndr.api.admin.repository.InvoiceRepository;
import us.fyndr.api.admin.repository.RevenueDetailsRepository;

class RevenueServiceTest {

    @Mock
    private RevenueDetailsRepository revenueDetailsRepository;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private IndividualRepository individualRepository;

    @InjectMocks
    private RevenueService revenueService;
    
    @BeforeEach
    public void setUp() {
      MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void revenueDetailsTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        String whereConditions = "";

        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();

        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45, "bizName1", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName1", "promo");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName2", "interaction");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(199.86, "bizName2", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);        
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(2);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedResult = preparePagedRevenueDetailsOutDTO();       
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void revenueDetailsEmptyFilterInputsTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        revenueDetailsInDTO.setBusinessName("");
        revenueDetailsInDTO.setCountry("");     
        
        String whereConditions = "";
        
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();
        
        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45, "bizName1", "offers");

        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName1", "promo");
        revenueDetailsList.add(revenueEntityDTO);       
        revenueEntityDTO = new RevenueDetails(97.43, "bizName2", "interaction");
        revenueDetailsList.add(revenueEntityDTO);       
        revenueEntityDTO = new RevenueDetails(199.86, "bizName2", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);    
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(2);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedResult = preparePagedRevenueDetailsOutDTO();
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void revenueDetailsCountResultTest() {
        
        int pageNumber = 0;
        int maxResults = 1;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        revenueDetailsInDTO.setBusinessName("");
        revenueDetailsInDTO.setCountry("");
        
        String whereConditions = "";
        
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();
        
        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45, "bizName1", "offers");

        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName1", "promo");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName2", "interaction");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(199.86, "bizName2", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);       
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(2);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedResult = preparePagedRevenueDetailsOutDTO();   
        expectedResult.setCount(2L);
        expectedResult.setLast(false);
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void revenueDetailsCountryFilterTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        revenueDetailsInDTO.setCountry("US");
        String whereConditions = " AND ( im.country = 'US' ) ";
        
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();
        
        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45, "bizName1", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName1", "promo");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName2", "interaction");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(199.86, "bizName2", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);    
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(2);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedResult = preparePagedRevenueDetailsOutDTO();
        
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void revenueDetailsDateFilterTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        revenueDetailsInDTO.setStartDate(LocalDate.of(2022, 9, 15));
        revenueDetailsInDTO.setEndDate(LocalDate.of(2022, 9, 25));
        String whereConditions = " AND ( i.invoice_dt BETWEEN '2022-09-15' AND '2022-09-25') ";
        
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();
        
        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45, "bizName1", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName1", "promo");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName2", "interaction");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(199.86, "bizName2", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);    
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(2);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedResult = preparePagedRevenueDetailsOutDTO();
        
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void revenueDetailsEndDateNullFilterTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        revenueDetailsInDTO.setStartDate(LocalDate.of(2022, 9, 15));
        revenueDetailsInDTO.setEndDate(null);
        String whereConditions = "";
        
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();
        
        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45, "bizName1", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName1", "promo");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName2", "interaction");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(199.86, "bizName2", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);    
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(2);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedResult = preparePagedRevenueDetailsOutDTO();
        
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void revenueDetailsEmptyResultTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        String whereConditions = " ";
        
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);    
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(0);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedResult = new PagedRevenueDetailsOutDTO();
        expectedResult.setLast(true);
        expectedResult.setCount(0L);
        
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedResult, actualResult);
    }
    
    @Test
    public void revenueDetailsNullResultTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        String whereConditions = " ";
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(null);    
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(0);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedResult = new PagedRevenueDetailsOutDTO();
        expectedResult.setLast(true);
        expectedResult.setCount(0L);
        
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedResult, actualResult);
    }
       
    @Test
    public void revenueDetailsBusinessFilterTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        revenueDetailsInDTO.setBusinessName("bizName1");
        String whereConditions = " AND ( bm.biz_name ILIKE '%bizName1%' ) ";
        
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();
        
        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45, "bizName1", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43, "bizName1", "promo");
        revenueDetailsList.add(revenueEntityDTO);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);    
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(1);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedPagedRevenueDetailsOutDTO  = new PagedRevenueDetailsOutDTO();
        
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        String businessName = "bizName1";
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(243.88);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setPromo(97.43);
        revenueDetailsOutDTO.setOffers(146.45);
        revenueDetailsOutDTO.setObjId(1L);
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        expectedPagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        expectedPagedRevenueDetailsOutDTO.setLast(true);
        expectedPagedRevenueDetailsOutDTO.setCount(1L);
      
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedPagedRevenueDetailsOutDTO, actualResult);
    }
    
    @Test
    public void revenueDetailsPrecisionTest() {
        
        int pageNumber = 0;
        int maxResults = 4;
        
        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        
        String whereConditions = "";
        
        List<RevenueDetails> revenueDetailsList = new ArrayList<>();
        
        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45456, "bizName1", "offers");
        revenueDetailsList.add(revenueEntityDTO);
        revenueEntityDTO = new RevenueDetails(97.43567, "bizName1", "promo");
        revenueDetailsList.add(revenueEntityDTO);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});
        
        when(revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults)).thenReturn(businessNamesList);
        when(revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList)).thenReturn(revenueDetailsList);    
        when(revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions)).thenReturn(1);
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName1")).thenReturn("USD");
        when(invoiceRepository.fetchCurrencyByBusinessName("bizName2")).thenReturn("INR");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName1")).thenReturn("$");
        when(invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2")).thenReturn("#");
        when(individualRepository.fetchObjIdByBusinessName("bizName1")).thenReturn(1L);
        when(individualRepository.fetchObjIdByBusinessName("bizName2")).thenReturn(2L);
        
        PagedRevenueDetailsOutDTO expectedPagedRevenueDetailsOutDTO  = new PagedRevenueDetailsOutDTO();

        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();

        String businessName = "bizName1";

        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(243.89);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setPromo(97.44);
        revenueDetailsOutDTO.setOffers(146.45);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setObjId(1L);

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        expectedPagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        expectedPagedRevenueDetailsOutDTO.setLast(true);
        expectedPagedRevenueDetailsOutDTO.setCount(1L);
      
        PagedRevenueDetailsOutDTO actualResult = revenueService.revenueDetails(pageNumber, maxResults, revenueDetailsInDTO);
        
        assertEquals(expectedPagedRevenueDetailsOutDTO, actualResult);
    }

    private PagedRevenueDetailsOutDTO preparePagedRevenueDetailsOutDTO() {
        
        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        String businessName = "bizName1";

        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(243.88);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setPromo(97.43);
        revenueDetailsOutDTO.setOffers(146.45);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setObjId(1L);

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);

        revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        businessName = "bizName2";
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(297.29);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("$");
        revenueDetailsOutDTO.setInteraction(97.43);
        revenueDetailsOutDTO.setOffers(199.86);
        revenueDetailsOutDTO.setCurrency("INR");
        revenueDetailsOutDTO.setCurrencySymbol("#");
        revenueDetailsOutDTO.setObjId(2L);
        
        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        
        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(true);
        pagedRevenueDetailsOutDTO.setCount(2L);
        return pagedRevenueDetailsOutDTO;
    }

}
