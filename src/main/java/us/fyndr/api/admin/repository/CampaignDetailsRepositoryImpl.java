package us.fyndr.api.admin.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.model.CampaignDetails;

/**
 * . CampaignDetailsRepositoryImpl is an implementation class of
 *         CampaignDetailsRepository Interface.
 */
@Repository
public class CampaignDetailsRepositoryImpl implements CampaignDetailsRepository {

    /**
     * The set of entities that can be managed by a given EntityManager instance is
     * defined by a persistence unit.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * getCampaignDetailByFiltersCriteria has query to fetch campaign details.
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to search campaign.
     * @param pageNumber      is the current page Number.
     * @param maxResults      is the total results per page.
     * @return List<CampaignDetails> This method filters all the campaign based on
     *         the search criteria: businessName, country, startDate, endDate
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CampaignDetails> getCampaignDetailByFiltersCriteria(final String whereConditions, final int pageNumber,
            final int maxResults) {
        Query query = em.createNativeQuery(
                "select sum(op.offer_price + op.tax) as totalOfferSoldAmount, c.objid as cmpnId, bm.biz_name as buisnessName, "
                        + "bm.biz_type as buisnessType, c.title as campaignName, c.end_dt as endDate, c.cmpn_type as "
                        + "campaignType from {h-schema}cmpn c "
                        + "left outer join {h-schema}cmpn_offer co on c.objid = co.cmpn_id "
                        + "left outer join {h-schema}offer_purchase op on co.objid = op.offer_id "
                        + "left outer join {h-schema}biz_master bm on bm.objid = c.bizid "
                        + "left outer join {h-schema}indv_master im on im.bizid = c.bizid "
                        + " where 1 = 1 " + whereConditions
                        + "group by c.objid, bm.biz_name, bm.biz_type, c.title "
                        + " order by bm.biz_name asc",
                "CampaignDetails");

        List<CampaignDetails> campaignDetailsList = query.setFirstResult(pageNumber * maxResults)
                .setMaxResults(maxResults).getResultList();

        return campaignDetailsList;
    }

    /**
     * countTotalCampaignsByFilterCriteria has query to count total campaigns.
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to fetch campaign details.
     * @return total count of fetch query.
     */
    public int countTotalCampaignsByFilterCriteria(final String whereConditions) {
        Query countQuery = em.createNativeQuery("select count(distinct(c.objid))  from {h-schema}cmpn c "
                + " left outer join {h-schema}cmpn_offer co on c.objid = co.cmpn_id "
                + " left outer join {h-schema}offer_purchase op on co.objid = op.offer_id "
                + " left outer join {h-schema}biz_master bm on bm.objid = c.bizid "
                + " left outer join {h-schema}indv_master im on im.bizid = c.bizid " + " where 1 = 1"
                + whereConditions);

        BigInteger bigInteger = (BigInteger) countQuery.getSingleResult();
        int total = bigInteger.intValue();
        return total;
    }

}
