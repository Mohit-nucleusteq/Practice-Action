package us.fyndr.api.admin.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.model.RevenueDetails;


/**
 * .Implementation class of RevenueDetailsRepository
 * contains methods to fetch revenue details for each business and invoice channel
 * and count of different businesses having paid invoices.
 */
@Repository
public class RevenueDetailsRepositoryImpl implements RevenueDetailsRepository {

    /**
     * The set of entities that can be managed by a given EntityManager instance is
     * defined by a persistence unit.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     *  @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to fetch revenue details.
     * @param pageNumber is the current page Number.
     * @param maxResultsPerPage is the total results per page.
     * @return businessNameList is list of businesses with different business names
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<String> fetchBusinessNamesByFilterCriteria(final String whereConditions,
            final int pageNumber, final int maxResultsPerPage) {

        Query query = em.createNativeQuery("SELECT distinct(bm.biz_name) FROM {h-schema}invoice i "
                + " join {h-schema}biz_master bm on i.bizid = bm.objid "
                + " join {h-schema}indv_master im on im.bizid = bm.objid "
                + " WHERE i.status='paid' "
                +  whereConditions
                + " order by bm.biz_name asc ");
        List<String> businessNameList = query.setFirstResult(pageNumber * maxResultsPerPage)
                                                       .setMaxResults(maxResultsPerPage)
                                                       .getResultList();
        return businessNameList;
    }
    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to fetch revenue details.
     * @param businessNamesList for fetching details of required businesses only.
     * @return revenueDetailsList is list of businesses with different invoice channels
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<RevenueDetails> fetchRevenueDetailsByFilterCriteria(final String whereConditions,
            final List<String> businessNamesList) {

        Query query = em.createNativeQuery(
                "SELECT sum(i.base_amount + i.tax_amount + i.tip_amount - i.discount_amount) as payment, "
                + " bm.biz_name as businessName, i.channel "
                + " FROM {h-schema}invoice i "
                + " join {h-schema}biz_master bm on i.bizid = bm.objid "
                + " join {h-schema}indv_master im on im.bizid = bm.objid"
                + " WHERE i.status='paid' "
                + whereConditions
                + " AND bm.biz_name IN ?1"
                + " GROUP BY i.channel, bm.biz_name "
                + " order by bm.biz_name asc", "RevenueDetails");

        query.setParameter(1, businessNamesList);
        List<RevenueDetails> revenueDetailsList = query.getResultList();

        return revenueDetailsList;
    }

    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to fetch revenue details.
     * @return count total number of rows query return.
     */
    @Override
    public int countTotalRevenueDetailsFetchByFilterCriteria(final String whereConditions) {
        Query countQuery = em.createNativeQuery(
                "SELECT COUNT(distinct(i.bizid)) FROM {h-schema}invoice i "
                + " join {h-schema}biz_master bm on i.bizid = bm.objid"
                + " join {h-schema}indv_master im on im.bizid = bm.objid"
                + " WHERE i.status='paid' "
                + whereConditions
               );

        BigInteger bigInteger = (BigInteger) countQuery.getSingleResult();
        int count = bigInteger.intValue();
        return count;
    }

}
