/**
*
 */
package us.fyndr.api.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.dbo.Individual;

/**
 * @author Prerna Goyal
 */
@Repository
public interface IndividualRepository extends JpaRepository<Individual, Long> {

    /**
     * @param businessName
     * @return objid
     */
    @Query("SELECT i.objId FROM Individual i JOIN Business bm ON i.businessId = bm.objId "
            + "WHERE bm.businessName= ?1 ")
    Long fetchObjIdByBusinessName(String businessName);
}
