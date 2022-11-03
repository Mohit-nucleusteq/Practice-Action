package us.fyndr.api.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import us.fyndr.api.admin.dbo.CodeMaster;

/**
 * @author saksh CodeMasterRepository interface handles all the DB operations
 *         related to code_master table.
 */
public interface CodeMasterRepository extends JpaRepository<CodeMaster, Long> {

}
