package us.fyndr.api.admin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;

/**
 * CampaignDetailsDTOTest is the Junit class for CampaignDetailsDTO Class.
 *
 */
public class CampaignDetailsTest {
    /**
     * testGetterAndSetter method tests the getters and setters of
     * CampaignDetailsDTOTest Class.
     */
    @Test
    public void testGetterAndSetter() {
        Integer cmpnId = 1;
        String buisnessName = "buisnessName";
        String campaignName = "campaignName";
        String buisnessType = "buisnessType";
        String campaignType = "campaignType";
        Double totalOfferSoldAmount = 12.23;
        Instant endDt = Instant.now();

        CampaignDetails campaignDetailDTO = new CampaignDetails(cmpnId, buisnessName, campaignName, buisnessType,
                campaignType, totalOfferSoldAmount, endDt);

        campaignDetailDTO.setBuisnessName(buisnessName);
        assertEquals(buisnessName, campaignDetailDTO.getBuisnessName());

        campaignDetailDTO.setCampaignName(campaignName);
        assertEquals(campaignName, campaignDetailDTO.getCampaignName());

        campaignDetailDTO.setBuisnessType(buisnessType);
        assertEquals(buisnessType, campaignDetailDTO.getBuisnessType());

        campaignDetailDTO.setCampaignType(campaignType);
        assertEquals(campaignType, campaignDetailDTO.getCampaignType());

        campaignDetailDTO.setBuisnessName(buisnessName);
        assertEquals(buisnessName, campaignDetailDTO.getBuisnessName());

        campaignDetailDTO.setTotalOfferSoldAmount(totalOfferSoldAmount);
        assertEquals(totalOfferSoldAmount, campaignDetailDTO.getTotalOfferSoldAmount());

        campaignDetailDTO.setCampaignId(cmpnId);
        assertEquals(cmpnId, campaignDetailDTO.getCampaignId());

        campaignDetailDTO.setEndDate(endDt);
        assertEquals(endDt, campaignDetailDTO.getEndDate());

    }

    @Test
    public void testToString() {

        Integer cmpnId = 1;
        String buisnessName = "buisnessName";
        String campaignName = "campaignName";
        String buisnessType = "buisnessType";
        String campaignType = "campaignType";
        Double totalOfferSoldAmount = 12.23;
        Instant endDate = Instant.now();

        CampaignDetails campaignDetailDTO = new CampaignDetails(cmpnId, buisnessName, campaignName, buisnessType,
                campaignType, totalOfferSoldAmount, endDate);

        assertEquals(
                "CampaignDetail [campaignId=1, buisnessName=buisnessName, campaignName=campaignName, buisnessType=buisnessType, campaignType=campaignType, totalOfferSoldAmount=12.23, endDate="
                        + endDate + "]",
                campaignDetailDTO.toString());

    }

    @Test
    public void testEqualsAndHashcode() {

        Instant endDt = Instant.now();

        CampaignDetails campaignDetailDTO1 = new CampaignDetails(1, "buisnessName", "campaignName", "buisnessType",
                "campaignType", 12.23, endDt);

        assertEquals(campaignDetailDTO1, campaignDetailDTO1);
        assertEquals(campaignDetailDTO1.hashCode(), campaignDetailDTO1.hashCode());

        assertNotEquals(campaignDetailDTO1, new Object());

        assertNotEquals(campaignDetailDTO1, null);

        CampaignDetails campaignDetailDTO2 = new CampaignDetails(1, "buisnessName", "campaignName", "buisnessType",
                "campaignType", 12.23, endDt);
        assertEquals(campaignDetailDTO1, campaignDetailDTO2);
        assertEquals(campaignDetailDTO1.hashCode(), campaignDetailDTO2.hashCode());

        campaignDetailDTO2 = new CampaignDetails(2, "buisnessName", "campaignName", "buisnessType", "campaignType",
                12.23, endDt);
        assertNotEquals(campaignDetailDTO1, campaignDetailDTO2);
        assertNotEquals(campaignDetailDTO1.hashCode(), campaignDetailDTO2.hashCode());

        campaignDetailDTO2 = new CampaignDetails(1, "buisnessName", "campaign", "buisnessType", "campaignType", 12.23,
                endDt);
        assertNotEquals(campaignDetailDTO1, campaignDetailDTO2);
        assertNotEquals(campaignDetailDTO1.hashCode(), campaignDetailDTO2.hashCode());

        campaignDetailDTO2 = new CampaignDetails(1, "buisness", "campaignName", "buisness", "campaignType", 12.23,
                endDt);
        assertNotEquals(campaignDetailDTO1, campaignDetailDTO2);
        assertNotEquals(campaignDetailDTO1.hashCode(), campaignDetailDTO2.hashCode());

        campaignDetailDTO2 = new CampaignDetails(1, "buisness", "campaignName", "buisnessType", "campaign", 12.23,
                Instant.now());
        assertNotEquals(campaignDetailDTO1, campaignDetailDTO2);
        assertNotEquals(campaignDetailDTO1.hashCode(), campaignDetailDTO2.hashCode());

        campaignDetailDTO2 = new CampaignDetails(1, "buisness", "campaignName", "buisnessType", "campaignType", 23.23,
                endDt);
        assertNotEquals(campaignDetailDTO1, campaignDetailDTO2);
        assertNotEquals(campaignDetailDTO1.hashCode(), campaignDetailDTO2.hashCode());

    }
}
