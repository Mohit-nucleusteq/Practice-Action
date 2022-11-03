package us.fyndr.api.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import us.fyndr.api.admin.dbo.Country;
import us.fyndr.api.admin.dto.CampaignStatisticsOutDTO;
import us.fyndr.api.admin.dto.RevenueStatisticsOutDTO;
import us.fyndr.api.admin.dto.UserStatisticsOutDTO;
import us.fyndr.api.admin.repository.CampaignRepository;
import us.fyndr.api.admin.repository.CountryRepository;
import us.fyndr.api.admin.repository.InvoiceRepository;
import us.fyndr.api.admin.repository.UserRepository;
import us.fyndr.api.admin.specification.StatisticsSpecification;

/**
 * StatisticsService class contains methods for the API which are directing to
 * StatisticsController class.
 *
 */
@Service
public class StatisticsService {

    /**
     * The CampaignRepository object.
     */
    @Autowired
    private CampaignRepository campaignRepository;

    /**
     * The UserRepository object.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * The invoiceRepository object.
     */
    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * The CountryRepository object.
     */
    @Autowired
    private CountryRepository countryRepository;

    /**
     * The StatisticsSpecification object.
     */
    @Autowired
    private StatisticsSpecification statisticsSpecification;

    /**
     * The logger variable to log information or errors related to StatisticsService
     * class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);

    /**
     * @return CampaignStatisticsOutDTO contains the fields which represents
     *         campaign statistics.
     */
    public CampaignStatisticsOutDTO getCampaignStatistics() {

        Long activeCampaignCount = campaignRepository
                .count(Specification.where(statisticsSpecification.findByActiveCampaign()));
        Long instoreCampaignCount = campaignRepository.count(Specification.where(
                statisticsSpecification.findByInstoreCampaign().and(statisticsSpecification.findByActiveCampaign())));
        Long onlineAndInstoreCampaignCount = campaignRepository.count(Specification.where(statisticsSpecification
                .findByOnlineAndInstoreCampaign().and(statisticsSpecification.findByActiveCampaign())));
        Long onlineCampaignCount = campaignRepository.count(Specification.where(
                statisticsSpecification.findByOnlineCampaign().and(statisticsSpecification.findByActiveCampaign())));

        CampaignStatisticsOutDTO campaignStatisticsOutDto = new CampaignStatisticsOutDTO();
        campaignStatisticsOutDto.setActive(activeCampaignCount);
        campaignStatisticsOutDto.setInstore(instoreCampaignCount);
        campaignStatisticsOutDto.setOnline(onlineCampaignCount);
        campaignStatisticsOutDto.setAll(onlineAndInstoreCampaignCount);

        LOGGER.info("Campaign Statistics : {}", campaignStatisticsOutDto.toString());
        return campaignStatisticsOutDto;
    }

    /**
     * @return UserStatisticsOutDTO contains the fields which represents user
     *         statistics.
     */
    public UserStatisticsOutDTO getUserStatistics() {

        UserStatisticsOutDTO userStatisticsOutDTO = new UserStatisticsOutDTO();

        Long merchant = userRepository.count(Specification
                .where(statisticsSpecification.findByActiveUsers().and(statisticsSpecification.findByMerchantUsers())));
        Long users = userRepository.count(Specification.where(statisticsSpecification.findByActiveUsers()));

        userStatisticsOutDTO.setCustomer(users - merchant);
        userStatisticsOutDTO.setMerchant(merchant);
        userStatisticsOutDTO.setUser(users);

        LOGGER.info("User Statistics : {}", userStatisticsOutDTO.toString());
        return userStatisticsOutDTO;
    }

    /**
     * @param country is isoCode of country
     * @return RevenueStatisticsOutDTO contains details about the statistics of
     *         revenue
     */
    public RevenueStatisticsOutDTO getRevenueStatistics(final String country) {

        Double offerRevenue = invoiceRepository.totalRevenueByChannel("offers", country);

        Double catalogueRevenue = invoiceRepository.totalRevenueByChannel("catalog", country);

        Double promotionRevenue = invoiceRepository.totalRevenueByChannel("promo", country);

        Double interactionRevenue = invoiceRepository.totalRevenueByChannel("interaction", country);

        Double totalRevenue = invoiceRepository.totalRevenue(country);

        Country currency = countryRepository.findByIsoCode(country);

        RevenueStatisticsOutDTO revenueStatisticsOutDTO = new RevenueStatisticsOutDTO();

        revenueStatisticsOutDTO.setOfferRevenue(offerRevenue);
        revenueStatisticsOutDTO.setCatalogueRevenue(catalogueRevenue);
        revenueStatisticsOutDTO.setPromotionalRevenue(promotionRevenue);
        revenueStatisticsOutDTO.setInteractionRevenue(interactionRevenue);
        revenueStatisticsOutDTO.setTotalRevenue(totalRevenue);
        revenueStatisticsOutDTO.setCurrency(currency.getCurrency());
        revenueStatisticsOutDTO.setCurrencySymbol(currency.getCurrencySymbol());

        return revenueStatisticsOutDTO;
    }

}
