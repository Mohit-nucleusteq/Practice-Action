package us.fyndr.api.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.dbo.Campaign;

/**
 * CampaignRepository interface extends JpaRepository interface which helps to perform db operations on Campaign Table.
 */
@Repository
public interface CampaignRepository extends JpaRepository<Campaign, Long>, JpaSpecificationExecutor<Campaign> {

}
