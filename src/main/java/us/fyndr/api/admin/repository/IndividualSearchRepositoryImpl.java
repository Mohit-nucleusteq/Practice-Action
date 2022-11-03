package us.fyndr.api.admin.repository;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.dbo.Individual;

/**
 * @author saksh IndividualSearchRepositoryImpl is an implementation class of
 *         IndividualSearchRepository Interface.
 */
@Repository
public class IndividualSearchRepositoryImpl implements IndividualSearchRepository {

    /**
     * The set of entities that can be managed by a given EntityManager instance is
     * defined by a persistence unit.
     */
    @PersistenceContext
    private EntityManager em;

    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to search users.
     * @param pageNumber      is the current page Number.
     * @param maxResults       is the total results per page.
     * @return List<Individual> object is a pagination object. This method filters
     *         all the users based on the search criteria: text, accountStatus,
     *         userType
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Individual> searchByFiltersCriteria(final String whereConditions, final int pageNumber,
            final int maxResults) {
        TypedQuery<Individual> typedQuery = (TypedQuery<Individual>) em.createNativeQuery(
                "SELECT im.objid, im.address_line1, im.address_line2, im.city, im.country, im.created_dt,"
                        + " im.email, im.first_name, im.last_name, im.fyndr_handle, im.is_business , im.phone,"
                        + " im.ctry_code, im.state, im.postal_code, im.bizid, im.account_status, im.gender, im.qrid, im.yob "
                        + " FROM {h-schema}indv_master im LEFT JOIN {h-schema}biz_master bm ON im.bizid = bm.objid WHERE 1 = 1 "
                        + whereConditions + " order by im.first_name, im.last_name asc",
                Individual.class);

        List<Individual> users = typedQuery.setFirstResult(pageNumber * maxResults).setMaxResults(maxResults)
                .getResultList();

        return users;
    }

    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to search users.
     * @return total count of search query.
     */
    public int countSearchByFiltersCriteria(final String whereConditions) {
        Query countQuery = em.createNativeQuery(
                "SELECT COUNT(*) FROM {h-schema}indv_master im LEFT JOIN {h-schema}biz_master bm "
                + "ON im.bizid = bm.objid WHERE 1 = 1 "
                        + whereConditions);

        BigInteger bigInteger = (BigInteger) countQuery.getSingleResult();
        int total = bigInteger.intValue();
        return total;
    }
}
