/**
*
 */
package us.fyndr.api.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.dbo.Business;

/**
 * @author saksh BusinessRepository Class handles all the DB operations on
 *         Business Table
 */
@Repository
public interface BusinessRepository extends JpaRepository<Business, Long> {

}
