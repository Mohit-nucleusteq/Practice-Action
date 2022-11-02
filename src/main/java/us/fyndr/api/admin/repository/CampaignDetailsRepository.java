package us.fyndr.api.admin.repository;

import java.util.List;
import us.fyndr.api.admin.model.CampaignDetails;

/**
 * CampaignDetailsRepository class contains custom functions for fetching campaign details.
 */
public interface CampaignDetailsRepository {

    /**
     * getCampaignDetailByFiltersCriteria has query to fetch campaign details.
     * @param whereConditions specifies the filter criteria based on which an admin
     * wants to fetch campaign details.
     * @param pageNumber is the current page Number.
     * @param maxResults is the total results per page.
     * @return list of campaign details
     */
    List<CampaignDetails> getCampaignDetailByFiltersCriteria(String whereConditions, int pageNumber, int maxResults);

    /**
     * countTotalCampaignsByFilterCriteria has query to count total campaigns.
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to fetch campaign details.
     * @return total count of fetch query.
     */
    int countTotalCampaignsByFilterCriteria(String whereConditions);

}
