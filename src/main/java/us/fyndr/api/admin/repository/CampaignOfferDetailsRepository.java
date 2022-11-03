package us.fyndr.api.admin.repository;

import us.fyndr.api.admin.model.CountCampaignOffers;

/**
 * CampaignOfferDetailsRepository contains custom functions for fetching count of
 *  campaign offers.
 */
public interface CampaignOfferDetailsRepository {

    /**
     * getCampaignOfferDetails method to fetch count of offers in a specific campaigns.
     * @param campaignId - campaign id
     * @return count of active offer, count of total offer, total offer sold.
     */
    CountCampaignOffers getCampaignOfferDetails(int campaignId);
}
