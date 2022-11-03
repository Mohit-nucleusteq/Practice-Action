package us.fyndr.api.admin.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.model.CountCampaignOffers;

/**
 * CampaignOfferDetailsRepositoryImpl implementation class of
 * CampaignOfferDetailsRepository interface.
 */
@Repository
public class CampaignOfferDetailsRepositoryImpl implements CampaignOfferDetailsRepository {

    /**
     * The set of entities that can be managed by a given EntityManager instance is
     * defined by a persistence unit.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * getCampaignOfferDetails method to fetch count of offers in a specific campaigns.
     * @param campaignId - campaign id
     * @return CountCampaignOffers - totalOffer, activeOffer and offerSold of a campaign
     */

    @Override
    public CountCampaignOffers getCampaignOfferDetails(final int campaignId) {
        Query query = em.createNativeQuery(
                "select  count(c.objid) as totalOffer, COUNT(CASE WHEN c.status = 'active' THEN 1 END)"
                        + " as activeOffer, sum(c.offer_sold) as offerSold FROM {h-schema}cmpn_offer c "
                        + "where c.cmpn_id = " + campaignId + " ",
                "CountCampaignOffers");

        CountCampaignOffers countCampaignOffers = (CountCampaignOffers) query.getSingleResult();

        return countCampaignOffers;
    }

}
