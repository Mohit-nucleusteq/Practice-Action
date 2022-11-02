package us.fyndr.api.admin.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.fyndr.api.admin.dto.CampaignDetailsInDTO;
import us.fyndr.api.admin.dto.CampaignDetailsOutDTO;
import us.fyndr.api.admin.dto.PagedCampaignDetailOutDTO;
import us.fyndr.api.admin.model.CampaignDetails;
import us.fyndr.api.admin.model.CountCampaignOffers;
import us.fyndr.api.admin.repository.CampaignDetailsRepository;
import us.fyndr.api.admin.repository.CampaignOfferDetailsRepository;
import us.fyndr.api.admin.repository.CountryRepository;
import us.fyndr.api.admin.repository.IndividualRepository;

/**
 * CampaignDetailsService class contains service methods for the API.
 */
@Service
public class CampaignDetailsService {

    /**
     * . The CampaignRepository object With the help of this repository we can get
     * the campaign details also get the count of fetch query.
     */
    @Autowired
    private CampaignDetailsRepository campaignDetailsRepository;

    /**
     * . The Campaign Offer Details Repository with the help of this repository we
     * can get the count of active offer, count of total offer, total offer sold.
     */
    @Autowired
    private CampaignOfferDetailsRepository campaignOfferDetailsRepository;

    /**
     * . The Individual Repository with the help of this we can get the objid on the
     * basis of business name.
     */
    @Autowired
    private IndividualRepository individualRepository;

    /**
     * The Country Repository with the help of this we can get the currency and
     * currency symbol.
     */
    @Autowired
    private CountryRepository countryRepository;

    /**
     * getCampaignDetails fetches campaign details .
     *
     * @param pageNumber is the current page Number.
     * @param maxResultsPerPage is the total results per page.
     * @param campaignDetailsInDTO contains the search criteria.
     * @return CampaignDetailsOutDTO contains the fields which represents campaign
     *         details.
     */
    public PagedCampaignDetailOutDTO getCampaignDetails(final int pageNumber, final int maxResultsPerPage,
            final CampaignDetailsInDTO campaignDetailsInDTO) {

        String businessName = campaignDetailsInDTO.getBusinessName();
        String country = campaignDetailsInDTO.getCountry();
        LocalDate startDate = campaignDetailsInDTO.getStartDate();
        LocalDate endDate = campaignDetailsInDTO.getEndDate();
        String whereConditions = "";

        if (Objects.nonNull(country) && !country.isEmpty()) {
            whereConditions = addCountrySearchCriteria(country, whereConditions);
        }

        if (Objects.nonNull(businessName) && !businessName.isEmpty()) {
            whereConditions = addBusinessNameSearchCriteria(businessName, whereConditions);
        }

        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            whereConditions = addDateRangeSearchCriteria(startDate, endDate, whereConditions);
        }

        List<CampaignDetails> campaignDetailList = campaignDetailsRepository
                .getCampaignDetailByFiltersCriteria(whereConditions, pageNumber, maxResultsPerPage);

        PagedCampaignDetailOutDTO pagedCampaignDetailsOutDTO = new PagedCampaignDetailOutDTO();
        if (Objects.nonNull(campaignDetailList) && !campaignDetailList.isEmpty()) {
            List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = prepareCampaignDetailsOutDTO(campaignDetailList);
            pagedCampaignDetailsOutDTO.setCampaignDetails(campaignDetailsOutDTOList);
        }

        int count = campaignDetailsRepository.countTotalCampaignsByFilterCriteria(whereConditions);

        boolean isLast = false;
        if (count <= (pageNumber + 1) * maxResultsPerPage) {
            isLast = true;
        }

        pagedCampaignDetailsOutDTO.setLast(isLast);
        pagedCampaignDetailsOutDTO.setCount((long) count);

        return pagedCampaignDetailsOutDTO;

    }

    /**
     * This method prepares CampaignDetailsOutDTO by iterating through list fetched
     * from repository.
     * @param campaignDetailList contain the details of campaign's.
     * @return List<CampaignDetailsOutDto> - list of campaigns with details
     */
    private List<CampaignDetailsOutDTO> prepareCampaignDetailsOutDTO(final List<CampaignDetails> campaignDetailList) {

        List<CampaignDetailsOutDTO> campaignDetailsOutDTOList = new ArrayList<CampaignDetailsOutDTO>();

        for (CampaignDetails campaignDetails : campaignDetailList) {
            CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

            if (Objects.nonNull(campaignDetails.getCampaignId())) {
                CountCampaignOffers countCampaignOffers = campaignOfferDetailsRepository
                        .getCampaignOfferDetails(campaignDetails.getCampaignId());

                campaignDetailsOutDTO.setActiveOffers(countCampaignOffers.getActiveOffer());
                campaignDetailsOutDTO.setTotalOffers(countCampaignOffers.getTotalOffer());
                campaignDetailsOutDTO.setOfferSold(countCampaignOffers.getOfferSold());
            }

            campaignDetailsOutDTO
                    .setObjId(individualRepository.fetchObjIdByBusinessName(campaignDetails.getBuisnessName()));
            campaignDetailsOutDTO
                    .setCurrency(countryRepository.getCurrencyByIndividualId(campaignDetailsOutDTO.getObjId()));
            campaignDetailsOutDTO.setCurrencySymbol(
                    countryRepository.getCurrencySymbolByIndividualId(campaignDetailsOutDTO.getObjId()));
            campaignDetailsOutDTO.setIndustryType(campaignDetails.getBuisnessType());
            campaignDetailsOutDTO.setCampaignName(campaignDetails.getCampaignName());
            campaignDetailsOutDTO.setBusinessName(campaignDetails.getBuisnessName());
            campaignDetailsOutDTO.setCampaignType(campaignDetails.getCampaignType());

            LocalDate endDate = campaignDetails.getEndDate().atZone(ZoneId.of("UTC")).toLocalDate();
            campaignDetailsOutDTO.setEndDate(endDate);
            campaignDetailsOutDTO.setTotalOfferSoldAmount(campaignDetails.getTotalOfferSoldAmount());
            campaignDetailsOutDTOList.add(campaignDetailsOutDTO);
        }
        return campaignDetailsOutDTOList;
    }

    /**
     * addCountrySearchCriteria helps to generate where condition on the basis of country.
     * @param country - country used for filter
     * @param whereCondition variable has the where clauses.
     * @return whereConditionForCountry appends whereCondition condition with country filter
     */
    private String addCountrySearchCriteria(final String country, final String whereCondition) {
        String whereConditionForCountry = whereCondition + " AND ( im.country = '" + country + "' ) ";
        return whereConditionForCountry;
    }

    /**
     * addBusinessNameSearchCriteria helps to generate where condition on the basis of business name.
     * @param businessName - business name used for filer
     * @param whereCondition variable has the where clauses.
     * @return whereConditionForBusiness appends whereCondition condition with businessName filter
     */
    private String addBusinessNameSearchCriteria(final String businessName, final String whereCondition) {
        String whereConditionForBusiness = whereCondition + " AND ( bm.biz_name ILIKE '%" + businessName + "%' ) ";
        return whereConditionForBusiness;

    }

    /**
     * addDateRangeSearchCriteria helps to generate where condition on the basis of start and end date.
     * @param startDate - start date from which results are needed.
     * @param endDate - end date till which results are needed.
     * @param whereCondition variable has the where clauses.
     * @return whereConditionForDate appends whereCondition condition with date filter
     */
    private String addDateRangeSearchCriteria(final LocalDate startDate, final LocalDate endDate,
            final String whereCondition) {
        String whereConditionForDate = whereCondition + " AND ( c.end_dt BETWEEN '" + startDate + "' AND '" + endDate
                + "') ";
        return whereConditionForDate;
    }
}
