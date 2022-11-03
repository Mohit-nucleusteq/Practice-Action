package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import us.fyndr.api.admin.dbo.Campaign;
import us.fyndr.api.admin.model.CampaignDetails;

@DataJpaTest
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql", "classpath:/dml/repository/campaign_details_insertion.sql" })
@ComponentScan(basePackages = { "us.fyndr.api.admin.repository" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@Import(CampaignDetailsRepositoryImpl.class)
class CampaignDetailsRepositoryImplTest {

    @Autowired
    private CampaignRepository campaignRepository;

    @Autowired
    private CampaignDetailsRepositoryImpl campaignDetailsRepositoryImpl;

    Instant endDate = Instant.now();

    @BeforeEach
    public void setup() {

        Campaign campaign = buildCampaign(101l, "campaignType", endDate, "goal", 1l, "campaignName", "active");
        campaignRepository.save(campaign);

        campaign = buildCampaign(102l, "campaignType1", endDate, "goal1", 2l, "campaignName1", "active");
        campaignRepository.save(campaign);

        campaign = buildCampaign(103l, "campaignType2", endDate, "goal2", 3l, "campaignName2", "expired");
        campaignRepository.save(campaign);

    }

   // @Test
    public void testCampaignDetailsWithoutPagination() {
        int pageNumber = 0;
        int maxResults = 3;

        String whereConditions = "";

        List<CampaignDetails> expectedCampaignDetail = new ArrayList<>();

        CampaignDetails campaignDetailDTO = new CampaignDetails(1, "a", "campaignName", "buisnessType", "campaignType",
                54.7, endDate);
        expectedCampaignDetail.add(campaignDetailDTO);

        campaignDetailDTO = new CampaignDetails(2, "b", "campaignName1", "buisnessType", "campaignType1", 54.3,
                endDate);
        expectedCampaignDetail.add(campaignDetailDTO);

        campaignDetailDTO = new CampaignDetails(3, "c", "campaignName2", "buisnessType2", "campaignType2", 54.7,
                endDate);
        expectedCampaignDetail.add(campaignDetailDTO);

        List<CampaignDetails> actualCampaignDetails = campaignDetailsRepositoryImpl
                .getCampaignDetailByFiltersCriteria(whereConditions, pageNumber, maxResults);

        assertEquals(expectedCampaignDetail, actualCampaignDetails);
        assertEquals(maxResults, actualCampaignDetails.size());

    }

    //@Test
    public void testCampaignDetailOrderByBizName() {
        int pageNumber = 0;
        int maxResults = 3;
        List<CampaignDetails> expectedCampaignDetail = new ArrayList<>();

        CampaignDetails campaignDetailDTO = new CampaignDetails(1, "a", "campaignName", "buisnessType", "campaignType",
                54.7, endDate);
        expectedCampaignDetail.add(campaignDetailDTO);
        campaignDetailDTO = new CampaignDetails(3, "c", "campaignName2", "buisnessType2", "campaignType2", 54.7,
                endDate);

        expectedCampaignDetail.add(campaignDetailDTO);

        campaignDetailDTO = new CampaignDetails(2, "b", "campaignName1", "buisnessType", "campaignType1", 54.3,
                endDate);
        expectedCampaignDetail.add(campaignDetailDTO);
        String whereConditions = "";

        List<CampaignDetails> actualCampaignDetails = campaignDetailsRepositoryImpl
                .getCampaignDetailByFiltersCriteria(whereConditions, pageNumber, maxResults);

        assertNotEquals(expectedCampaignDetail, actualCampaignDetails);
        assertEquals(maxResults, actualCampaignDetails.size());
    }

    //@Test
    public void testCampaignDetailsPagination() {
        int pageNumber = 0;
        int maxResults = 3;

        String whereConditions = "";

        List<CampaignDetails> expectedCampaignDetail = new ArrayList<>();

        CampaignDetails campaignDetailDTO = new CampaignDetails(1, "a", "campaignName", "buisnessType", "campaignType",
                54.7, endDate);
        expectedCampaignDetail.add(campaignDetailDTO);

        campaignDetailDTO = new CampaignDetails(2, "b", "campaignName1", "buisnessType", "campaignType1", 54.3,
                endDate);
        expectedCampaignDetail.add(campaignDetailDTO);

        campaignDetailDTO = new CampaignDetails(3, "c", "campaignName2", "buisnessType2", "campaignType2", 54.7,
                endDate);
        expectedCampaignDetail.add(campaignDetailDTO);

        List<CampaignDetails> actualCampaignDetails = campaignDetailsRepositoryImpl
                .getCampaignDetailByFiltersCriteria(whereConditions, pageNumber, maxResults);

        assertEquals(expectedCampaignDetail, actualCampaignDetails);
        assertEquals(maxResults, actualCampaignDetails.size());

        int pageNumber1 = 0;
        int maxResults1 = 1;

        String whereConditions1 = "";

        List<CampaignDetails> expectedCampaignDetail1 = new ArrayList<>();

        CampaignDetails campaignDetailDTO1 = new CampaignDetails(1, "a", "campaignName", "buisnessType", "campaignType",
                54.7, endDate);
        expectedCampaignDetail1.add(campaignDetailDTO1);

        List<CampaignDetails> actualCampaignDetails1 = campaignDetailsRepositoryImpl
                .getCampaignDetailByFiltersCriteria(whereConditions1, pageNumber1, maxResults1);

        assertEquals(expectedCampaignDetail1, actualCampaignDetails1);
        assertEquals(maxResults1, actualCampaignDetails1.size());

        pageNumber1 = 1;
        maxResults1 = 1;

        whereConditions1 = "";

        expectedCampaignDetail1 = new ArrayList<>();

        campaignDetailDTO1 = new CampaignDetails(2, "b", "campaignName1", "buisnessType", "campaignType1", 54.3,
                endDate);
        expectedCampaignDetail1.add(campaignDetailDTO1);

        actualCampaignDetails1 = campaignDetailsRepositoryImpl.getCampaignDetailByFiltersCriteria(whereConditions1,
                pageNumber1, maxResults1);

        assertEquals(expectedCampaignDetail1, actualCampaignDetails1);
        assertEquals(maxResults1, actualCampaignDetails1.size());

        pageNumber1 = 2;
        maxResults1 = 1;

        whereConditions1 = "";

        expectedCampaignDetail1 = new ArrayList<>();

        campaignDetailDTO1 = new CampaignDetails(3, "c", "campaignName2", "buisnessType2", "campaignType2", 54.7,
                endDate);
        expectedCampaignDetail1.add(campaignDetailDTO1);

        actualCampaignDetails1 = campaignDetailsRepositoryImpl.getCampaignDetailByFiltersCriteria(whereConditions1,
                pageNumber1, maxResults1);

        assertEquals(expectedCampaignDetail1, actualCampaignDetails1);
        assertEquals(maxResults1, actualCampaignDetails1.size());

        pageNumber1 = 3;
        maxResults1 = 1;

        whereConditions1 = "";

        expectedCampaignDetail1 = new ArrayList<>();

        actualCampaignDetails1 = campaignDetailsRepositoryImpl.getCampaignDetailByFiltersCriteria(whereConditions1,
                pageNumber1, maxResults1);

        assertEquals(expectedCampaignDetail1, actualCampaignDetails1);
        assertEquals(0, actualCampaignDetails1.size());

    }

    //@Test
    public void testCountCampaignDetails() {

        String whereConditions = "";

        int actualCount = campaignDetailsRepositoryImpl.countTotalCampaignsByFilterCriteria(whereConditions);

        assertEquals(3, actualCount);
    }

    private Campaign buildCampaign(Long bizId, String campaignType, Instant endDate, String goal, Long objId,
            String title, String status) {

        Campaign campaignDBO = new Campaign();

        campaignDBO.setBusinessId(bizId);
        campaignDBO.setCampaignType(campaignType);
        campaignDBO.setEndDate(endDate);
        campaignDBO.setGoal(goal);
        campaignDBO.setObjId(objId);
        campaignDBO.setStatus(status);
        campaignDBO.setTitle(title);

        return campaignDBO;
    }

}
