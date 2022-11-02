package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class CampaignDetailOutDTOTest {
    /**
     * testGetterAndSetter method tests the getters and setters of
     * CampaignDetailOutDTOTest Class.
     */
    @Test
    public void testGetterAndSetter() {
        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        assertNull(campaignDetailsOutDTO.getBusinessName());
        String businessName = "buisnessName";
        campaignDetailsOutDTO.setBusinessName(businessName);
        assertEquals(businessName, campaignDetailsOutDTO.getBusinessName());

        assertNull(campaignDetailsOutDTO.getCampaignName());
        String campaignName = "campaignName";
        campaignDetailsOutDTO.setCampaignName(campaignName);
        assertEquals(campaignName, campaignDetailsOutDTO.getCampaignName());

        assertNull(campaignDetailsOutDTO.getActiveOffers());
        Integer activeOffers = 12;
        campaignDetailsOutDTO.setActiveOffers(12);
        assertEquals(activeOffers, campaignDetailsOutDTO.getActiveOffers());

        assertNull(campaignDetailsOutDTO.getCampaignType());
        String campaignType = "campaignType";
        campaignDetailsOutDTO.setCampaignType(campaignType);
        assertEquals(campaignType, campaignDetailsOutDTO.getCampaignType());

        assertNull(campaignDetailsOutDTO.getEndDate());
        LocalDate endDt = LocalDate.now();
        campaignDetailsOutDTO.setEndDate(endDt);
        assertEquals(endDt, campaignDetailsOutDTO.getEndDate());

        assertNull(campaignDetailsOutDTO.getIndustryType());
        String insdustryType = "insdustryType";
        campaignDetailsOutDTO.setIndustryType(insdustryType);
        assertEquals(insdustryType, campaignDetailsOutDTO.getIndustryType());

        assertNull(campaignDetailsOutDTO.getOfferSold());
        Integer offerSold = 12;
        campaignDetailsOutDTO.setOfferSold(offerSold);
        assertEquals(offerSold, campaignDetailsOutDTO.getOfferSold());

        assertEquals(0.0, campaignDetailsOutDTO.getTotalOfferSoldAmount());
        Double totalTotalOfferSoldAmount1 = 12.32;
        campaignDetailsOutDTO.setTotalOfferSoldAmount(totalTotalOfferSoldAmount1);
        assertEquals(12.32, campaignDetailsOutDTO.getTotalOfferSoldAmount());

        assertNull(campaignDetailsOutDTO.getTotalOffers());
        Integer totalOffer = 12;
        campaignDetailsOutDTO.setTotalOffers(totalOffer);
        assertEquals(totalOffer, campaignDetailsOutDTO.getActiveOffers());

        assertNull(campaignDetailsOutDTO.getCurrency());
        String currency = "USD";
        campaignDetailsOutDTO.setCurrency(currency);
        assertEquals(currency, campaignDetailsOutDTO.getCurrency());

        assertNull(campaignDetailsOutDTO.getCurrencySymbol());
        String currencySymbol = "$";
        campaignDetailsOutDTO.setCurrency(currencySymbol);
        assertEquals(currencySymbol, campaignDetailsOutDTO.getCurrency());

        assertNull(campaignDetailsOutDTO.getObjId());
        Long objid = 1l;
        campaignDetailsOutDTO.setObjId(objid);
        assertEquals(objid, campaignDetailsOutDTO.getObjId());

    }

    /**
     * testToString method tests the toString method of CampaignDetailsInDTO Class.
     */
    @Test
    public void testToString() {
        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        String businessName = "businessName";
        campaignDetailsOutDTO.setBusinessName(businessName);
        Integer activeOffer = 12;
        campaignDetailsOutDTO.setActiveOffers(activeOffer);
        String campaignName = "campaignName";
        campaignDetailsOutDTO.setCampaignName(campaignName);
        String campaignType = "campaignType";
        campaignDetailsOutDTO.setCampaignType(campaignType);
        LocalDate endDate = LocalDate.now();
        campaignDetailsOutDTO.setEndDate(endDate);
        Integer offerSold = 12;
        campaignDetailsOutDTO.setOfferSold(offerSold);
        String industryType = "industryType";
        campaignDetailsOutDTO.setIndustryType(industryType);
        Double totalOfferSoldAmount = 12.23;
        campaignDetailsOutDTO.setTotalOfferSoldAmount(totalOfferSoldAmount);
        Integer totalOffer = 11;
        campaignDetailsOutDTO.setTotalOffers(totalOffer);
        String curreny = "USD";
        campaignDetailsOutDTO.setCurrency(curreny);
        String currencySymbol = "$";
        campaignDetailsOutDTO.setCurrencySymbol(currencySymbol);
        Long objid = 1l;
        campaignDetailsOutDTO.setObjId(objid);

        assertEquals(
                "CampaignDetailsOutDTO [objId=1, businessName=businessName, campaignName=campaignName, activeOffers=12, totalOffers=11, industryType=industryType, campaignType=campaignType, offerSold=12, totalOfferSoldAmount=12.23, endDate="
                        + campaignDetailsOutDTO.getEndDate() + ", currency=USD, currencySymbol=$]",
                campaignDetailsOutDTO.toString());
    }

    @Test
    public void testEqualsAndHashcode() {

        String businessName = "businessName";

        Integer activeOffer = 12;

        String campaignName = "campaignName";

        String campaignType = "campaignType";

        LocalDate endDt = LocalDate.now();
        
        Integer offerSold = 12;

        String industryType = "industryType";

        Double totalOfferSoldAmount = 12.23;

        Integer totalOffer = 11;

        String currency = "$";

        String currencySymbol = "USD";
        
        Long objid = 1l;

        CampaignDetailsOutDTO campaignDetailsOutDTO1 = buildCampaignDetailsOutDTO(businessName, activeOffer,
                campaignName, campaignType, endDt, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency,
                currencySymbol, objid);

        assertEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO1);
        assertEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO1.hashCode());

        assertNotEquals(campaignDetailsOutDTO1, new Object());

        CampaignDetailsOutDTO campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer,
                campaignName, campaignType, endDt, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency,
                currencySymbol, objid);

        assertEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName + " ", activeOffer, campaignName, campaignType,
                endDt, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, 111, campaignName, campaignType, endDt,
                offerSold, industryType, totalOfferSoldAmount, totalOffer, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName + " ", campaignType,
                endDt, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType + " ",
                endDt, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType,
                LocalDate.MIN, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType,
                endDt, 0, industryType, totalOfferSoldAmount, totalOffer, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType,
                endDt, offerSold, industryType + " ", totalOfferSoldAmount, totalOffer, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType,
                endDt, offerSold, industryType, 13.0, totalOffer, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType,
                endDt, offerSold, industryType, totalOfferSoldAmount, 1212, currency, currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType,
                endDt, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency + " ", currencySymbol, objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType,
                endDt, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency, currencySymbol + " ", objid);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());
        
        campaignDetailsOutDTO2 = buildCampaignDetailsOutDTO(businessName, activeOffer, campaignName, campaignType,
                endDt, offerSold, industryType, totalOfferSoldAmount, totalOffer, currency, currencySymbol, 2l);

        assertNotEquals(campaignDetailsOutDTO1, campaignDetailsOutDTO2);
        assertNotEquals(campaignDetailsOutDTO1.hashCode(), campaignDetailsOutDTO2.hashCode());

    }

    private CampaignDetailsOutDTO buildCampaignDetailsOutDTO(String businessName, Integer activeOffer,
            String campaignName, String campaignType, LocalDate endDate, Integer offerSold, String industryType,
            Double totalOfferSoldAmount, Integer totalOffer, String currency, String currenSymbol, Long objid) {

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();

        campaignDetailsOutDTO.setBusinessName(businessName);

        campaignDetailsOutDTO.setCampaignName(campaignName);

        campaignDetailsOutDTO.setCampaignType(campaignType);

        campaignDetailsOutDTO.setActiveOffers(activeOffer);

        campaignDetailsOutDTO.setEndDate(endDate);

        campaignDetailsOutDTO.setTotalOffers(totalOffer);

        campaignDetailsOutDTO.setOfferSold(offerSold);

        campaignDetailsOutDTO.setIndustryType(industryType);

        campaignDetailsOutDTO.setTotalOfferSoldAmount(totalOfferSoldAmount);

        campaignDetailsOutDTO.setCurrency(currency);

        campaignDetailsOutDTO.setCurrencySymbol(currenSymbol);
        
        campaignDetailsOutDTO.setObjId(objid);

        return campaignDetailsOutDTO;
    }

}
