package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

/**
 * CampaignStatisticsOutDTOTest is the Junit class for CampaignStatisticsOutDTO Class.
 *
 */
class CampaignStatisticsOutDTOTest {

    /**
     * testGetterAndSetter method tests the getters and setters of CampaignStatisticsOutDTO Class.
     */
    @Test
    public void testGetterAndSetter() {
        CampaignStatisticsOutDTO campaignStatisticsOutDTO = new CampaignStatisticsOutDTO();

        assertNull(campaignStatisticsOutDTO.getActive());
        Long active = 200L;
        campaignStatisticsOutDTO.setActive(active);
        assertEquals(active, campaignStatisticsOutDTO.getActive());

        assertNull(campaignStatisticsOutDTO.getOnline());
        Long online = 100L;
        campaignStatisticsOutDTO.setOnline(online);
        assertEquals(online, campaignStatisticsOutDTO.getOnline());

        assertNull(campaignStatisticsOutDTO.getInstore());
        Long instore = 50L;
        campaignStatisticsOutDTO.setInstore(instore);
        assertEquals(instore, campaignStatisticsOutDTO.getInstore());

        assertNull(campaignStatisticsOutDTO.getAll());
        Long all = 25L;
        campaignStatisticsOutDTO.setAll(all);
        assertEquals(all, campaignStatisticsOutDTO.getAll());
    }

    /**
     * testToString method tests the toString method of CampaignStatisticsOutDTO Class.
     */
    @Test
    public void testToString() {
        CampaignStatisticsOutDTO campaignStatisticsOutDTO = new CampaignStatisticsOutDTO();

        Long active = 200L;
        campaignStatisticsOutDTO.setActive(active);

        Long online = 100L;
        campaignStatisticsOutDTO.setOnline(online);

        Long instore = 10L;
        campaignStatisticsOutDTO.setInstore(instore);

        Long all = 90L;
        campaignStatisticsOutDTO.setAll(all);

        assertEquals("CampaignStatisticsOutDTO [active=200, online=100, instore=10, all=90]",
                campaignStatisticsOutDTO.toString());
    }

    /**
     * testHashCodeAndEqual method tests the hashcode and equals method of CampaignStatisticsOutDTO Class.
     */
    @Test
    public void testHashCodeAndEqual() {
        Long active = 200L;
        Long online = 100L;
        Long instore = 10L;
        Long all = 90L;

        CampaignStatisticsOutDTO campaignStatisticsOutDTO1 = setData(active, online, instore, all);

        assertEquals(campaignStatisticsOutDTO1, campaignStatisticsOutDTO1);
        assertEquals(campaignStatisticsOutDTO1.hashCode(), campaignStatisticsOutDTO1.hashCode());

        assertNotEquals(campaignStatisticsOutDTO1, new Object());

        CampaignStatisticsOutDTO campaignStatisticsOutDTO2 = setData(active, online, instore, all);
        assertEquals(campaignStatisticsOutDTO1, campaignStatisticsOutDTO2);
        assertEquals(campaignStatisticsOutDTO1.hashCode(), campaignStatisticsOutDTO2.hashCode());

        Long active2 = 250L;

        campaignStatisticsOutDTO2 = setData(active2, online, instore, all);
        assertNotEquals(campaignStatisticsOutDTO1, campaignStatisticsOutDTO2);
        assertNotEquals(campaignStatisticsOutDTO1.hashCode(), campaignStatisticsOutDTO2.hashCode());

        Long online2 = 50L;
        
        campaignStatisticsOutDTO2 = setData(active, online2, instore, all);
        assertNotEquals(campaignStatisticsOutDTO1, campaignStatisticsOutDTO2);
        assertNotEquals(campaignStatisticsOutDTO1.hashCode(), campaignStatisticsOutDTO2.hashCode());

        Long instore2 = 150L;
        campaignStatisticsOutDTO2 = setData(active, online, instore2, all);
        assertNotEquals(campaignStatisticsOutDTO1, campaignStatisticsOutDTO2);
        assertNotEquals(campaignStatisticsOutDTO1.hashCode(), campaignStatisticsOutDTO2.hashCode());

        Long all2 = 50L;
        campaignStatisticsOutDTO2 = setData(active, online, instore, all2);
        assertNotEquals(campaignStatisticsOutDTO1, campaignStatisticsOutDTO2);
        assertNotEquals(campaignStatisticsOutDTO1.hashCode(), campaignStatisticsOutDTO2.hashCode());

        campaignStatisticsOutDTO1 = new CampaignStatisticsOutDTO();
        campaignStatisticsOutDTO2 = new CampaignStatisticsOutDTO();

        assertEquals(campaignStatisticsOutDTO1, campaignStatisticsOutDTO2);
        assertEquals(campaignStatisticsOutDTO1.hashCode(), campaignStatisticsOutDTO2.hashCode());
    }

    /**
     * @param active - number of active campaigns.
     * @param online - number of campaigns whose goal is 'online'.
     * @param instore - number of campaigns whose goal is 'instore'.
     * @param all - number of campaigns whose goal is 'all'.
     * @return CampaignStatisticsOutDTO - sets the data.
     */
    private CampaignStatisticsOutDTO setData(Long active , Long online, Long instore, Long all) {
        CampaignStatisticsOutDTO campaignStatisticsOutDTO = new CampaignStatisticsOutDTO();

        campaignStatisticsOutDTO.setActive(active);
        campaignStatisticsOutDTO.setOnline(online);
        campaignStatisticsOutDTO.setInstore(instore);
        campaignStatisticsOutDTO.setAll(all);
        return campaignStatisticsOutDTO;
    }

}
