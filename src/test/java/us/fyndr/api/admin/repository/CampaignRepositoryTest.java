package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import us.fyndr.api.admin.dbo.Campaign;
import us.fyndr.api.admin.specification.StatisticsSpecification;

/**
 * CampaignRepositoryTest class has junit to check StatisticsSpecification class functions.
 *
 */
@DataJpaTest
@Sql(scripts = {"classpath:/ddl/schema-ddl.sql"})
public class CampaignRepositoryTest {

    /**
     * The CampaignRepository object.
     */
    @Autowired
    private CampaignRepository campaignRepository;
    
    /**
     * The StatisticsSpecification object.
     */
    private StatisticsSpecification statisticsSpecification = new StatisticsSpecification();

    /**
     * The Campaign object.
     */
    private Campaign campaign;

    /**
     * Setup before each testcase
     */
    @BeforeEach
    public void setUp() {
        campaign = new Campaign();
        campaign.setObjId(1L);
        campaign.setGoal("instore");
        campaign.setStatus("active");
        campaignRepository.save(campaign);
        
        campaign = new Campaign();
        campaign.setObjId(2L);
        campaign.setGoal("instore");
        campaign.setStatus("expired");
        campaignRepository.save(campaign);
        
        campaign = new Campaign();
        campaign.setObjId(3L);
        campaign.setGoal("online");
        campaign.setStatus("active");
        campaignRepository.save(campaign);
        
        campaign = new Campaign();
        campaign.setObjId(4L);
        campaign.setGoal("all");
        campaign.setStatus("active");
        campaignRepository.save(campaign);
        
        campaign = new Campaign();
        campaign.setObjId(5L);
        campaign.setGoal("all");
        campaign.setStatus("active");
        campaignRepository.save(campaign);
    }
 
    /**
     * testCountForAllActiveCampaigns testcase is to check the count for active campaigns.
     */
    public void testCountForAllActiveCampaigns() {
        Long expectedActiveCampaigns = 4L;

       Long activeCampaigns = campaignRepository.count(Specification.where(statisticsSpecification.findByActiveCampaign()));
              
        assertEquals(expectedActiveCampaigns, activeCampaigns);
    }

    /**
     * testCountForOnlineActiveCampaigns testcase is to check the count for active campaigns whose goal is online.
     */
    public void testCountForOnlineActiveCampaigns() {
        Long expectedOnlineActiveCampaigns = 1L;

        Long activeOnlineCampaigns = campaignRepository.count(Specification.where(
                statisticsSpecification.findByOnlineCampaign().and(statisticsSpecification.findByActiveCampaign())));

        assertEquals(expectedOnlineActiveCampaigns, activeOnlineCampaigns);
    }

    /**
     * testCountForInstoreActiveCampaigns testcase is to check the count for active campaigns whose goal is instore.
     */
    public void testCountForInstoreActiveCampaigns() {
        Long expectedInstoreActiveCampaigns = 1L;

        Long activeInstoreCampaigns = campaignRepository.count(Specification.where(
                statisticsSpecification.findByInstoreCampaign().and(statisticsSpecification.findByActiveCampaign())));

        assertEquals(expectedInstoreActiveCampaigns, activeInstoreCampaigns);
    }

    /**
     * testCountForOnlineAndInstoreActiveCampaigns testcase is to check the count for active campaigns whose goal is all.
     */
    public void testCountForOnlineAndInstoreActiveCampaigns() {
        Long expectedOnlineAndInstoreActiveCampaigns = 2L;

        Long activeOnlineAndInstoreCampaigns = campaignRepository.count(Specification.where(
                statisticsSpecification.findByOnlineAndInstoreCampaign().and(statisticsSpecification.findByActiveCampaign())));

        assertEquals(expectedOnlineAndInstoreActiveCampaigns, activeOnlineAndInstoreCampaigns);
    }
}

