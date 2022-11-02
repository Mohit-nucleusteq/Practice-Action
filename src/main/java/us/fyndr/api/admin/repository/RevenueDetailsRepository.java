package us.fyndr.api.admin.repository;

import java.util.List;

import us.fyndr.api.admin.model.RevenueDetails;

/**
 * RevenueDetailsRepository contains methods to fetch details from invoice table.
 */
public interface RevenueDetailsRepository {

    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to fetch revenue details.
     * @param pageNumber is the current page Number.
     * @param maxResultsPerPage is the total results per page.
     * @return list of all distinct business names
     */
    List<String> fetchBusinessNamesByFilterCriteria(String whereConditions, int pageNumber, int maxResultsPerPage);

    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to fetch revenue details.
     * @param buinessNamesList for fetching details of required businesses only
     * @return RevenueDetails
     */
    List<RevenueDetails> fetchRevenueDetailsByFilterCriteria(String whereConditions,
            List<String> buinessNamesList);

    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to fetch revenue details.
     * @return total count of fetch query.
     */
    int countTotalRevenueDetailsFetchByFilterCriteria(String whereConditions);

}
