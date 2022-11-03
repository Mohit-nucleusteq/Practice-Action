package us.fyndr.api.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.dbo.Individual;

/**
 * UserRepository contains methods to access the data from individual table.
 *
 */
@Repository
public interface UserRepository extends JpaRepository<Individual, Long>, JpaSpecificationExecutor<Individual>  {

}
