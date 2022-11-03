package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;

import org.joda.time.Instant;
import org.junit.jupiter.api.Test;

/**
 * CampaignDetailsInDTOTest is the Junit class for CampaignDetailsInDTO Class.
 *
 */
public class CampaignDetailsInDTOTest {
    /**
     * testGetterAndSetter method tests the getters and setters of
     * CampaignDetailsInDTOTest Class.
     */
    @Test
    public void testGetterAndSetter() {
        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();

        assertNull(campaignDetailsInDTO.getBusinessName());
        String businessName = "buisnessName";
        campaignDetailsInDTO.setBusinessName(businessName);
        assertEquals(businessName, campaignDetailsInDTO.getBusinessName());

        assertNull(campaignDetailsInDTO.getCountry());
        String country = "country";
        campaignDetailsInDTO.setCountry(country);
        assertEquals(country, campaignDetailsInDTO.getCountry());

        assertNull(campaignDetailsInDTO.getEndDate());
        LocalDate endDate = LocalDate.now();
        campaignDetailsInDTO.setEndDate(endDate);
        assertEquals(endDate, campaignDetailsInDTO.getEndDate());

        assertNull(campaignDetailsInDTO.getStartDate());
        LocalDate startDate = LocalDate.now();
        campaignDetailsInDTO.setStartDate(startDate);
        assertEquals(startDate, campaignDetailsInDTO.getStartDate());

    }

    /**
     * testToString method tests the toString method of CampaignStatisticsOutDTO
     * Class.
     */
    @Test
    public void testToString() {
        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();

        String businessName = "businessName";
        campaignDetailsInDTO.setBusinessName(businessName);

        LocalDate endDate = LocalDate.now();
        campaignDetailsInDTO.setEndDate(endDate);

        LocalDate startDate = LocalDate.now();
        campaignDetailsInDTO.setStartDate(startDate);

        String country = "country";
        campaignDetailsInDTO.setCountry(country);

        assertEquals("CampaignDetailsInDTO [businessName=businessName, country=country, startDate=" + startDate
                + ", endDate=" + endDate + "]", campaignDetailsInDTO.toString());
    }

    private CampaignDetailsInDTO buildCampaignDetailsInDTO(String businessName, String country, LocalDate endDate,
            LocalDate startDate) {

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();

        campaignDetailsInDTO.setBusinessName(businessName);
        campaignDetailsInDTO.setCountry(country);
        campaignDetailsInDTO.setEndDate(endDate);
        campaignDetailsInDTO.setStartDate(startDate);
        return campaignDetailsInDTO;
    }

    @Test
    public void testEqualsAndHashcode() {

        String businessName = "businessName";
        String country = "country";
        LocalDate endDate = LocalDate.now();
        LocalDate startDate = LocalDate.now();

        CampaignDetailsInDTO campaignDetailsInDTO1 = buildCampaignDetailsInDTO(businessName, country, endDate,
                startDate);

        assertEquals(campaignDetailsInDTO1, campaignDetailsInDTO1);
        assertEquals(campaignDetailsInDTO1.hashCode(), campaignDetailsInDTO1.hashCode());

        assertNotEquals(campaignDetailsInDTO1, new Object());

        CampaignDetailsInDTO campaignDetailsInDTO2 = buildCampaignDetailsInDTO(businessName, country, endDate,
                startDate);

        assertEquals(campaignDetailsInDTO1, campaignDetailsInDTO2);
        assertEquals(campaignDetailsInDTO1.hashCode(), campaignDetailsInDTO2.hashCode());

        campaignDetailsInDTO2 = buildCampaignDetailsInDTO(businessName + " ", country, endDate, startDate);

        assertNotEquals(campaignDetailsInDTO1, campaignDetailsInDTO2);
        assertNotEquals(campaignDetailsInDTO1.hashCode(), campaignDetailsInDTO2.hashCode());

        campaignDetailsInDTO2 = buildCampaignDetailsInDTO(businessName, country + " ", endDate, startDate);

        assertNotEquals(campaignDetailsInDTO1, campaignDetailsInDTO2);
        assertNotEquals(campaignDetailsInDTO1.hashCode(), campaignDetailsInDTO2.hashCode());
        campaignDetailsInDTO2 = buildCampaignDetailsInDTO(businessName, country + " ", LocalDate.MIN, startDate);

        assertNotEquals(campaignDetailsInDTO1, campaignDetailsInDTO2);
        assertNotEquals(campaignDetailsInDTO1.hashCode(), campaignDetailsInDTO2.hashCode());
        campaignDetailsInDTO2 = buildCampaignDetailsInDTO(businessName, country + " ", endDate, LocalDate.MAX);

        assertNotEquals(campaignDetailsInDTO1, campaignDetailsInDTO2);
        assertNotEquals(campaignDetailsInDTO1.hashCode(), campaignDetailsInDTO2.hashCode());

    }
}
