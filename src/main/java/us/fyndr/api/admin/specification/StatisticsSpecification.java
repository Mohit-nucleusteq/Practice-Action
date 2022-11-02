package us.fyndr.api.admin.specification;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.Campaign;
import us.fyndr.api.admin.dbo.Individual;

/**
 * StatisticsSpecification class contains JPA Specification methods to perform
 * the operations related to statistics.
 */
@Component
public class StatisticsSpecification {

    /**
     * @return Specification<Campaign> findByActiveCampaign method will return all
     *         the campaigns whose status is 'active'
     */
    public Specification<Campaign> findByActiveCampaign() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("status"), "active");
        };
    }

    /**
     * @return Specification<Campaign> findByInstoreCampaign method will return all
     *         the campaigns whose goal is 'instore'
     */
    public Specification<Campaign> findByInstoreCampaign() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("goal"), "instore");
        };
    }

    /**
     * @return Specification<Campaign> findByOnlineCampaign method will return all
     *         the campaigns whose goal is 'online'
     */
    public Specification<Campaign> findByOnlineCampaign() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("goal"), "online");
        };
    }

    /**
     * @return Specification<Campaign> findByOnlineCampaign method will return all
     *         the campaigns whose goal is 'all'
     */
    public Specification<Campaign> findByOnlineAndInstoreCampaign() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("goal"), "all");
        };
    }

    /**
     * @return Specification<Individual> findByActiveUsers method will return all
     *         the users whose status is active.
     */
    public Specification<Individual> findByActiveUsers() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("accountStatus"), AccountStatus.ACTIVE);
        };
    }

    /**
     * @return Specification<Individual> findByMerchantUsers method will return all
     *         the business users.
     */
    public Specification<Individual> findByMerchantUsers() {
        return (root, query, criteriaBuilder) -> {
            return criteriaBuilder.equal(root.get("isBusiness"), true);
        };
    }

}
