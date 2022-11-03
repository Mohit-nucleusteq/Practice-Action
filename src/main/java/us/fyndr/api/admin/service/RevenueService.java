package us.fyndr.api.admin.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.fyndr.api.admin.dto.PagedRevenueDetailsOutDTO;
import us.fyndr.api.admin.dto.RevenueDetailsInDTO;
import us.fyndr.api.admin.dto.RevenueDetailsOutDTO;
import us.fyndr.api.admin.model.RevenueDetails;
import us.fyndr.api.admin.repository.IndividualRepository;
import us.fyndr.api.admin.repository.InvoiceRepository;
import us.fyndr.api.admin.repository.RevenueDetailsRepository;

/**
 * The RevenueService contains methods to fetch revenue details.
 * Fetches details for businesses and their different invoice channels.
 */
@Service
public class RevenueService {

    /**
     * The revenueDetailsRepository object.
     */
    @Autowired
    private RevenueDetailsRepository revenueDetailsRepository;

    /**
     * The invoiceRepository object.
     */
    @Autowired
    private InvoiceRepository invoiceRepository;

    /**
     * The individualRepository object.
     */
    @Autowired
    private IndividualRepository individualRepository;

    /**
     * revenueDetails fetches revenue details for a business.
     * @param pageNumber is the current page Number.
     * @param maxResultsPerPage is the total results per page.
     * @param revenueDetailsInDTO has three criteria - Country, bizName
     * @return PagedRevenueDetailsOutDTO is a class represents the result in pagination.
     */
    public PagedRevenueDetailsOutDTO revenueDetails(final int pageNumber,
            final int maxResultsPerPage,
            final RevenueDetailsInDTO revenueDetailsInDTO) {

        String country = revenueDetailsInDTO.getCountry();
        String businessName = revenueDetailsInDTO.getBusinessName();
        LocalDate startDate = revenueDetailsInDTO.getStartDate();
        LocalDate endDate = revenueDetailsInDTO.getEndDate();

        String whereConditions = "";

        if (Objects.nonNull(country) && !country.isEmpty()) {
            whereConditions = addCountryInFetchRevenueCriteria(country, whereConditions);
        }

        if (Objects.nonNull(businessName) && !businessName.isEmpty()) {
            whereConditions = addBusinessNameInFetchRevenueCriteria(businessName, whereConditions);
        }

        if (Objects.nonNull(startDate) && Objects.nonNull(endDate)) {
            whereConditions = addDateRangeInFetchRevenueCriteria(startDate, endDate, whereConditions);
        }

        List<String> businessNamesList = revenueDetailsRepository.fetchBusinessNamesByFilterCriteria(whereConditions,
                pageNumber, maxResultsPerPage);

        List<RevenueDetails> revenueDetailsList = revenueDetailsRepository.fetchRevenueDetailsByFilterCriteria(whereConditions,
                businessNamesList);

        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();

        if (Objects.nonNull(revenueDetailsList) && !revenueDetailsList.isEmpty()) {
            List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = prepareRevenueDetailsOutDTO(revenueDetailsList);
            pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        }

        int totalResults = revenueDetailsRepository.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions);

        boolean isLast = false;
        if (totalResults <= (pageNumber + 1) * maxResultsPerPage) {
            isLast = true;
        }

        pagedRevenueDetailsOutDTO.setLast(isLast);
        pagedRevenueDetailsOutDTO.setCount((long) totalResults);
        return pagedRevenueDetailsOutDTO;
    }

    /**
     * @param country
     * @param whereCondition
     * @return whereCondition appending the country clause
     */
    private String addCountryInFetchRevenueCriteria(final String country, final String whereCondition) {

        String whereConditionForCountry = whereCondition + " AND ( im.country = '" + country + "' ) ";
        return whereConditionForCountry;
    }

    /**
     * @param businessName
     * @param whereCondition
     * @return whereCondition appending the businessName clause
     */
    private String addBusinessNameInFetchRevenueCriteria(final String businessName, final String whereCondition) {

        String whereConditionForCountry = whereCondition + " AND ( bm.biz_name ILIKE '%" + businessName + "%' ) ";
        return whereConditionForCountry;
    }

    private String addDateRangeInFetchRevenueCriteria(final LocalDate startDate,
            final LocalDate endDate, final String whereCondition) {

        String whereConditionForCountry = whereCondition
                + " AND ( i.invoice_dt BETWEEN '" + startDate + "' AND '"
                + endDate + "') ";
        return whereConditionForCountry;
    }

    /**
     * This method prepares RevenueDetailsOutDTO by iterating through list fetched from repository.
     * @param revenueDetailsList
     * @return List <RevenueDetailsOutDTO>
     */
    private List<RevenueDetailsOutDTO> prepareRevenueDetailsOutDTO(final List<RevenueDetails> revenueDetailsList) {

      // Building Map of business and its channel revenues
      Map<String, Map<String, Double>> businessChannelRevenueMap = revenueDetailsList
              .stream()
              .collect(Collectors.groupingBy(RevenueDetails::getBusinessName,
                      Collectors.toMap(RevenueDetails::getChannel,
                                       RevenueDetails::getPayment
                              )));

      // Building Map of business and its total revenue
      Map<String, Double> totalRevenueMap = businessChannelRevenueMap.entrySet()
              .stream()
              .collect(Collectors.groupingBy(entry -> entry.getKey(),
                      Collectors.summingDouble(entry -> entry.getValue()
                              .values().stream().reduce(Double::sum).get())));

      SortedSet<String> distinctBusinessSet = new TreeSet<String>(businessChannelRevenueMap.keySet());

      List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<RevenueDetailsOutDTO>();

      for (String businessName : distinctBusinessSet) {

          RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();

          revenueDetailsOutDTO.setBusinessName(businessName);
          revenueDetailsOutDTO.setCatalog(
                  businessChannelRevenueMap.get(businessName).get("catalog"));
          revenueDetailsOutDTO.setOffers(
                  businessChannelRevenueMap.get(businessName).get("offers"));
          revenueDetailsOutDTO.setInteraction(businessChannelRevenueMap
                  .get(businessName).get("interaction"));
          revenueDetailsOutDTO.setPromo(
                  businessChannelRevenueMap.get(businessName).get("promo"));
          revenueDetailsOutDTO
                  .setTotalRevenue(totalRevenueMap.get(businessName));
          revenueDetailsOutDTO.setCurrency(
                  invoiceRepository.fetchCurrencyByBusinessName(businessName));
          revenueDetailsOutDTO.setCurrencySymbol(invoiceRepository
                  .fetchCurrencySymbolByBusinessName(businessName));
          revenueDetailsOutDTO.setObjId(individualRepository.fetchObjIdByBusinessName(businessName));

          revenueDetailsOutDTOList.add(revenueDetailsOutDTO);

      }
      return revenueDetailsOutDTOList;
    }
}
