package us.fyndr.api.admin.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

/**
 * CampaignDBOTest Class is the Junit class for Campaign Class.
 *
 */
public class CampaignTest {

    /**
     * testGetterAndSetter method tests setters and getters of Campaign DBO Class.
     */
    @Test
    public void testGetterAndSetter() {
        Campaign campaignDBO = new Campaign();

        assertNull(campaignDBO.getObjId());
        Long objId = 2L;
        campaignDBO.setObjId(objId);
        assertEquals(objId, campaignDBO.getObjId());

        assertNull(campaignDBO.getGoal());
        String goal = "instore";
        campaignDBO.setGoal(goal);
        assertEquals(goal, campaignDBO.getGoal());

        assertNull(campaignDBO.getStatus());
        String status = "active";
        campaignDBO.setStatus(status);
        assertEquals(status, campaignDBO.getStatus());

        assertNull(campaignDBO.getBusinessId());
        Long bizId = 101l;
        campaignDBO.setBusinessId(bizId);
        assertEquals(bizId, campaignDBO.getBusinessId());

        assertNull(campaignDBO.getCampaignType());
        String cmpnType = "cmpnType";
        campaignDBO.setCampaignType(cmpnType);
        assertEquals(cmpnType, campaignDBO.getCampaignType());

        assertNull(campaignDBO.getEndDate());
        Instant endDate = Instant.now();
        campaignDBO.setEndDate(endDate);
        assertEquals(endDate, campaignDBO.getEndDate());

        assertNull(campaignDBO.getTitle());
        String title = "title";
        campaignDBO.setTitle(title);
        assertEquals(title, campaignDBO.getTitle());
    }

    /**
     * testToString method tests toString() method of Campaign DBO Class.
     */
    @Test
    public void testToString() {
        Campaign campaignDBO = new Campaign();

        String status = "active";
        campaignDBO.setStatus(status);

        long objid = 2;
        campaignDBO.setObjId(objid);

        String goal = "instore";
        campaignDBO.setGoal(goal);

        String cmpnType = "cmpnType";
        campaignDBO.setCampaignType(cmpnType);

        Long businessId = 102l;
        campaignDBO.setBusinessId(businessId);

        Instant endDate = Instant.now();
        campaignDBO.setEndDate(endDate);

        String title = "title";
        campaignDBO.setTitle(title);

        assertEquals(
                "Campaign [objId=2, goal=instore, status=active, businessId=102, campaignType=cmpnType, title=title, endDate="
                        + endDate + "]",
                campaignDBO.toString());

    }

    /**
     * testEqualsAndHashcode tests the hashcode and equals method of Campaign DBO Class.
     */
    @Test
    public void testEqualsAndHashcode() {
        String status = "active";
        long objId = 2;
        String goal = "instore";
        String campaignType = "cmpnType";
        Long businessId = 102l;
        Instant endDate = Instant.now();
        String title = "title";

        Campaign campaignDBO1 = buildCampaignDbo(status, objId, goal, endDate, campaignType, businessId, title);

        assertEquals(campaignDBO1, campaignDBO1);
        assertEquals(campaignDBO1.hashCode(), campaignDBO1.hashCode());
        assertNotEquals(campaignDBO1, new Object());

        Campaign campaignDBO2 = buildCampaignDbo(status, objId, goal, endDate, campaignType, businessId, title);
        assertEquals(campaignDBO1, campaignDBO2);
        assertEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());

        campaignDBO2 = buildCampaignDbo(status + " ", objId, goal, endDate, campaignType, businessId, title);
        assertNotEquals(campaignDBO1, campaignDBO2);
        assertNotEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());

        campaignDBO2 = buildCampaignDbo(status, 12, goal, endDate, campaignType, businessId, title);
        assertNotEquals(campaignDBO1, campaignDBO2);
        assertNotEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());

        campaignDBO2 = buildCampaignDbo(status, objId, goal + " ", endDate, campaignType, businessId, title);
        assertNotEquals(campaignDBO1, campaignDBO2);
        assertNotEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());

        campaignDBO2 = buildCampaignDbo(status, objId, goal, Instant.MIN, campaignType, businessId, title);
        assertNotEquals(campaignDBO1, campaignDBO2);
        assertNotEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());
        
        campaignDBO2 = buildCampaignDbo(status, objId, goal, endDate, campaignType + " ", businessId, title);
        assertNotEquals(campaignDBO1, campaignDBO2);
        assertNotEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());
        
        campaignDBO2 = buildCampaignDbo(status, objId, goal, endDate, campaignType, 1l, title);
        assertNotEquals(campaignDBO1, campaignDBO2);
        assertNotEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());

        campaignDBO2 = buildCampaignDbo(status, objId, goal, endDate, campaignType, businessId, title + " ");
        assertNotEquals(campaignDBO1, campaignDBO2);
        assertNotEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());

        campaignDBO1 = new Campaign();
        campaignDBO2 = new Campaign();
        assertEquals(campaignDBO1, campaignDBO2);
        assertEquals(campaignDBO1.hashCode(), campaignDBO2.hashCode());
    }

    /**
     * @param status
     * @param objid
     * @param goal
     * @param endDate
     * @param campaignType
     * @param businessId
     * @param title
     * @return Campaign object
     */
    private Campaign buildCampaignDbo(String status, long objid, String goal, Instant endDate, String campaignType,
            Long businessId, String title) {

        Campaign campaignDBO = new Campaign();

        campaignDBO.setGoal(goal);
        campaignDBO.setObjId(objid);
        campaignDBO.setStatus(status);
        campaignDBO.setBusinessId(businessId);
        campaignDBO.setCampaignType(campaignType);
        campaignDBO.setEndDate(endDate);
        campaignDBO.setTitle(title);

        return campaignDBO;
    }
}
