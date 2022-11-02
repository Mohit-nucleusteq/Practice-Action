package us.fyndr.api.admin.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CampaignOfferTest {

    @Test
    public void testGetterAndSetter() {

        CampaignOffer campaignOffer = new CampaignOffer();

        assertNull(campaignOffer.getObjId());
        Long objId = 4L;
        campaignOffer.setObjId(4L);
        assertEquals(objId, campaignOffer.getObjId());

        assertNull(campaignOffer.getCampaignId());
        Long cmpnId = 4L;
        campaignOffer.setCampaignId(4L);
        assertEquals(cmpnId, campaignOffer.getObjId());

        assertNull(campaignOffer.getOfferSold());
        Integer offerSold = 4;
        campaignOffer.setOfferSold(4);
        assertEquals(offerSold, campaignOffer.getOfferSold());

        assertNull(campaignOffer.getStatus());
        String status = "active";
        campaignOffer.setStatus("active");
        assertEquals(status, campaignOffer.getStatus());

        assertNull(campaignOffer.getCurrency());
        String currency = "USD";
        campaignOffer.setCurrency("USD");
        assertEquals(currency, campaignOffer.getCurrency());

        assertNull(campaignOffer.getCurrencySymbol());
        String currencySymbol = "$";
        campaignOffer.setCurrencySymbol(currencySymbol);
        assertEquals(currencySymbol, campaignOffer.getCurrencySymbol());
    }

    @Test
    public void testToString() {
        CampaignOffer campaignOffer = new CampaignOffer();

        Long objId = 4L;
        campaignOffer.setObjId(objId);

        Integer offerSold = 4;
        campaignOffer.setOfferSold(offerSold);

        Long cmpnId = 4l;
        campaignOffer.setCampaignId(cmpnId);

        String status = "active";
        campaignOffer.setStatus(status);

        String currency = "USD";
        campaignOffer.setCurrency(currency);

        String currencySymbol = "$";
        campaignOffer.setCurrencySymbol(currencySymbol);

        assertEquals(
                "CampaignOffer [objId=4, offerSold=4, campaignId=4, status=active, currency=USD, currencySymbol=$]",
                campaignOffer.toString());

    }

    @Test
    public void testEqualsAndHashcode() {
        Long objId = 4L;
        Long cmpnId = 4l;
        Integer offerSold = 2;
        String status = "active";
        String currency = "USD";
        String currencySymbol = "$";

        CampaignOffer campaignOffer1 = new CampaignOffer();
        campaignOffer1.setObjId(objId);
        campaignOffer1.setCampaignId(cmpnId);
        campaignOffer1.setOfferSold(offerSold);
        campaignOffer1.setStatus(status);
        campaignOffer1.setCurrency(currency);
        campaignOffer1.setCurrencySymbol(currencySymbol);

        assertEquals(campaignOffer1, campaignOffer1);
        assertEquals(campaignOffer1.hashCode(), campaignOffer1.hashCode());
        assertNotEquals(campaignOffer1, new Object());

        CampaignOffer campaignOffer2 = new CampaignOffer();
        campaignOffer2.setObjId(objId);
        campaignOffer2.setOfferSold(offerSold);
        campaignOffer2.setCampaignId(cmpnId);
        campaignOffer2.setStatus(status);
        campaignOffer2.setCurrencySymbol(currencySymbol);
        campaignOffer2.setCurrency(currency);
        assertEquals(campaignOffer1, campaignOffer2);
        assertEquals(campaignOffer1.hashCode(), campaignOffer2.hashCode());

        campaignOffer2.setObjId(2L);
        assertNotEquals(campaignOffer1, campaignOffer2);
        assertNotEquals(campaignOffer1.hashCode(), campaignOffer2.hashCode());

        campaignOffer2.setCampaignId(2L);
        assertNotEquals(campaignOffer1, campaignOffer2);
        assertNotEquals(campaignOffer1.hashCode(), campaignOffer2.hashCode());

        campaignOffer2.setOfferSold(2);
        assertNotEquals(campaignOffer1, campaignOffer2);
        assertNotEquals(campaignOffer1.hashCode(), campaignOffer2.hashCode());

        campaignOffer2.setStatus("inactive");
        assertNotEquals(campaignOffer1, campaignOffer2);
        assertNotEquals(campaignOffer1.hashCode(), campaignOffer2.hashCode());

        campaignOffer2.setCurrency("RS");
        assertNotEquals(campaignOffer1, campaignOffer2);
        assertNotEquals(campaignOffer1.hashCode(), campaignOffer2.hashCode());

        campaignOffer2.setCurrencySymbol("%");
        assertNotEquals(campaignOffer1, campaignOffer2);
        assertNotEquals(campaignOffer1.hashCode(), campaignOffer2.hashCode());

        campaignOffer1 = new CampaignOffer();
        campaignOffer2 = new CampaignOffer();
        assertEquals(campaignOffer1, campaignOffer2);
        assertEquals(campaignOffer1.hashCode(), campaignOffer2.hashCode());

    }
}
