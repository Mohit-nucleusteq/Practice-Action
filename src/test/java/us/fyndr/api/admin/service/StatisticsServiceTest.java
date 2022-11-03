
package us.fyndr.api.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;

import us.fyndr.api.admin.dbo.Campaign;
import us.fyndr.api.admin.dbo.Country;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dto.CampaignStatisticsOutDTO;
import us.fyndr.api.admin.dto.RevenueStatisticsOutDTO;
import us.fyndr.api.admin.dto.UserStatisticsOutDTO;
import us.fyndr.api.admin.repository.CampaignRepository;
import us.fyndr.api.admin.repository.CountryRepository;
import us.fyndr.api.admin.repository.InvoiceRepository;
import us.fyndr.api.admin.repository.UserRepository;
import us.fyndr.api.admin.specification.StatisticsSpecification;

class StatisticsServiceTest {

    /**
     * The StatisticsService class object.
     */
    @InjectMocks
    private StatisticsService statisticsService;

    /**
     * The CampaignRepository class object.
     */
    @Mock
    private CampaignRepository campaignRepository;

    /**
     * The UserRepository class object.
     */
    @Mock
    private UserRepository userRepository;

    /**
     * The invoiceRepository object.
     */
    @Mock
    private InvoiceRepository invoiceRepository;

    /**
     * The countryRepository object.
     */
    @Mock
    private CountryRepository countryRepository;

    /**
     * The statisticsSpecification object.
     */
    @Mock
    private StatisticsSpecification statisticsSpecification;

    /**
     * setup method will set the initial configuration required to run the
     * testcases.
     */
    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * testGetCampaignStatistics method tests the service layer functionality for
     * campaignStatistics.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetCampaignStatistics() {
        CampaignStatisticsOutDTO campaignStatisticsOutDTO = new CampaignStatisticsOutDTO();

        Long active = 200L;
        campaignStatisticsOutDTO.setActive(active);

        Long online = 100L;
        campaignStatisticsOutDTO.setOnline(online);

        Long instore = 49L;
        campaignStatisticsOutDTO.setInstore(instore);

        Long all = 51L;
        campaignStatisticsOutDTO.setAll(all);

        Specification<Campaign> specificationCampaign = Mockito.mock(Specification.class);
        Mockito.when(statisticsSpecification.findByActiveCampaign()).thenReturn(specificationCampaign);

        Specification<Campaign> specsCampaign2 = Mockito.mock(Specification.class);
        Mockito.when(specsCampaign2.and(specificationCampaign)).thenReturn(specsCampaign2);
        Mockito.when(statisticsSpecification.findByInstoreCampaign()).thenReturn(specsCampaign2);

        Specification<Campaign> specsCampaign3 = Mockito.mock(Specification.class);
        Mockito.when(specsCampaign3.and(specificationCampaign)).thenReturn(specsCampaign3);
        Mockito.when(statisticsSpecification.findByOnlineCampaign()).thenReturn(specsCampaign3);

        Specification<Campaign> specsCampaign4 = Mockito.mock(Specification.class);
        Mockito.when(specsCampaign4.and(specificationCampaign)).thenReturn(specsCampaign4);
        Mockito.when(statisticsSpecification.findByOnlineAndInstoreCampaign()).thenReturn(specsCampaign4);

        Mockito.when(campaignRepository.count(Specification.where(statisticsSpecification.findByActiveCampaign())))
                .thenReturn(active);
        Mockito.when(campaignRepository.count(Specification.where(
                statisticsSpecification.findByOnlineCampaign().and(statisticsSpecification.findByActiveCampaign()))))
                .thenReturn(online);
        Mockito.when(campaignRepository.count(Specification.where(
                statisticsSpecification.findByInstoreCampaign().and(statisticsSpecification.findByActiveCampaign()))))
                .thenReturn(instore);
        Mockito.when(campaignRepository.count(Specification.where(statisticsSpecification
                .findByOnlineAndInstoreCampaign().and(statisticsSpecification.findByActiveCampaign()))))
                .thenReturn(all);

        CampaignStatisticsOutDTO campaignStatisticsOutDTO2 = statisticsService.getCampaignStatistics();

        Mockito.verify(campaignRepository, Mockito.times(1)).count(
                statisticsSpecification.findByOnlineCampaign().and(statisticsSpecification.findByActiveCampaign()));
        Mockito.verify(campaignRepository, Mockito.times(1)).count(
                statisticsSpecification.findByInstoreCampaign().and(statisticsSpecification.findByActiveCampaign()));
        Mockito.verify(campaignRepository, Mockito.times(1)).count(statisticsSpecification.findByActiveCampaign());
        Mockito.verify(campaignRepository, Mockito.times(1)).count(statisticsSpecification
                .findByOnlineAndInstoreCampaign().and(statisticsSpecification.findByActiveCampaign()));
        Mockito.verify(statisticsSpecification, Mockito.times(3)).findByOnlineCampaign();
        Mockito.verify(statisticsSpecification, Mockito.times(3)).findByInstoreCampaign();
        Mockito.verify(statisticsSpecification, Mockito.times(12)).findByActiveCampaign();
        Mockito.verify(statisticsSpecification, Mockito.times(3)).findByOnlineAndInstoreCampaign();

        assertEquals(campaignStatisticsOutDTO.getActive(), campaignStatisticsOutDTO2.getActive());
        assertEquals(campaignStatisticsOutDTO.getOnline(), campaignStatisticsOutDTO2.getOnline());
        assertEquals(campaignStatisticsOutDTO.getInstore(), campaignStatisticsOutDTO2.getInstore());
        assertEquals(campaignStatisticsOutDTO.getAll(), campaignStatisticsOutDTO2.getAll());
    }

    /**
     * testGetCampaignStatistics method tests the service layer functionality for
     * userStatistics.
     */
    @Test
    @SuppressWarnings("unchecked")
    public void testGetUserStatistics() {
        UserStatisticsOutDTO userStatisticsOutDTO = new UserStatisticsOutDTO();
        Long user = 20L;
        userStatisticsOutDTO.setUser(user);

        Long merchant = 5L;
        userStatisticsOutDTO.setMerchant(merchant);

        userStatisticsOutDTO.setCustomer(user - merchant);

        Specification<Individual> specificationIndividual = Mockito.mock(Specification.class);
        Mockito.when(statisticsSpecification.findByActiveUsers()).thenReturn(specificationIndividual);

        Specification<Individual> specificationIndividual2 = Mockito.mock(Specification.class);
        Mockito.when(specificationIndividual.and(specificationIndividual2)).thenReturn(specificationIndividual2);
        Mockito.when((specificationIndividual).and(statisticsSpecification.findByMerchantUsers()))
                .thenReturn(specificationIndividual2);

        Mockito.when(userRepository.count(Specification
                .where(statisticsSpecification.findByActiveUsers().and(statisticsSpecification.findByMerchantUsers()))))
                .thenReturn(merchant);

        Mockito.when(userRepository.count(Specification.where(statisticsSpecification.findByActiveUsers())))
                .thenReturn(user);

        UserStatisticsOutDTO userStatisticsOutDTO1 = statisticsService.getUserStatistics();
        assertEquals(userStatisticsOutDTO.getUser(), userStatisticsOutDTO1.getUser());
        assertEquals(userStatisticsOutDTO.getCustomer(), userStatisticsOutDTO1.getCustomer());
        assertEquals(userStatisticsOutDTO.getMerchant(), userStatisticsOutDTO1.getMerchant());
        Mockito.verify(userRepository, Mockito.times(1)).count(statisticsSpecification.findByActiveUsers());
        Mockito.verify(userRepository, Mockito.times(1))
                .count(statisticsSpecification.findByActiveUsers().and(statisticsSpecification.findByMerchantUsers()));
        Mockito.verify(statisticsSpecification, Mockito.times(6)).findByActiveUsers();
        Mockito.verify(statisticsSpecification, Mockito.times(4)).findByMerchantUsers();
    }

    @Test
    public void testGetRevenueStatistics() {
        RevenueStatisticsOutDTO expectedRevenueStatisticsOutDTO = new RevenueStatisticsOutDTO();

        Double offerRevenue = 45.56;
        expectedRevenueStatisticsOutDTO.setOfferRevenue(offerRevenue);

        Double catalogueRevenue = 34.25;
        expectedRevenueStatisticsOutDTO.setCatalogueRevenue(catalogueRevenue);

        Double promotionRevenue = 24.45;
        expectedRevenueStatisticsOutDTO.setPromotionalRevenue(promotionRevenue);

        Double interactionRevenue = 224.5;
        expectedRevenueStatisticsOutDTO.setInteractionRevenue(interactionRevenue);

        Double totalRevenue = 6535.32;
        expectedRevenueStatisticsOutDTO.setTotalRevenue(totalRevenue);

        String currency = "US";
        expectedRevenueStatisticsOutDTO.setCurrency(currency);

        String currencySymbol = "#";
        expectedRevenueStatisticsOutDTO.setCurrencySymbol(currencySymbol);

        String isoCode = "US";

        Double catalogue = 34.2545;
        Mockito.when(invoiceRepository.totalRevenueByChannel("catalog", isoCode)).thenReturn(catalogue);

        Double offers = 45.56;
        Mockito.when(invoiceRepository.totalRevenueByChannel("offers", isoCode)).thenReturn(offers);

        Double promotion = 24.454;
        Mockito.when(invoiceRepository.totalRevenueByChannel("promo", isoCode)).thenReturn(promotion);

        Double interaction = 224.5;
        Mockito.when(invoiceRepository.totalRevenueByChannel("interaction", isoCode)).thenReturn(interaction);

        Double totalPayment = 6535.32344435;
        Mockito.when(invoiceRepository.totalRevenue(isoCode)).thenReturn(totalPayment);

        Country country = new Country();
        country.setCurrency("US");
        country.setCurrencySymbol("#");
        country.setIsoCode("US");
        country.setObjId(5L);
        Mockito.when(countryRepository.findByIsoCode(isoCode)).thenReturn(country);

        RevenueStatisticsOutDTO actualRevenueStatisticsOutDTO = statisticsService.getRevenueStatistics(isoCode);

        assertEquals(expectedRevenueStatisticsOutDTO, actualRevenueStatisticsOutDTO);

        Mockito.verify(invoiceRepository, Mockito.times(1)).totalRevenue(isoCode);
        Mockito.verify(invoiceRepository, Mockito.times(1)).totalRevenueByChannel("offers", isoCode);
        Mockito.verify(invoiceRepository, Mockito.times(1)).totalRevenueByChannel("promo", isoCode);
        Mockito.verify(invoiceRepository, Mockito.times(1)).totalRevenueByChannel("interaction", isoCode);
        Mockito.verify(invoiceRepository, Mockito.times(1)).totalRevenueByChannel("catalog", isoCode);

    }
}
