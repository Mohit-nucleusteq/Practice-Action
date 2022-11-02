package us.fyndr.api.admin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Instant;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.model.CampaignDetails;
import us.fyndr.api.admin.model.CountCampaignOffers;

public class CountCampaignOffersTest {
    @Test
    public void testGetterAndSetter() {
        Integer activeOffer = 12;
        Integer totalOffer = 20;
        Integer offerSold = 8;

        CountCampaignOffers countCampaignOffers = new CountCampaignOffers(activeOffer, totalOffer, offerSold);

        countCampaignOffers.setActiveOffer(activeOffer);
        assertEquals(activeOffer, countCampaignOffers.getActiveOffer());

        countCampaignOffers.setOfferSold(offerSold);
        assertEquals(offerSold, countCampaignOffers.getOfferSold());

        countCampaignOffers.setTotalOffer(totalOffer);
        assertEquals(totalOffer, countCampaignOffers.getTotalOffer());

    }

    @Test
    public void testToString() {

        Integer activeOffer = 12;
        Integer totalOffer = 20;
        Integer offerSold = 8;

        CountCampaignOffers countCampaignOffers = new CountCampaignOffers(activeOffer, totalOffer, offerSold);

        assertEquals("CountCampaignOffers [activeOffer=12, totalOffer=20, offerSold=8]",
                countCampaignOffers.toString());

    }

    @Test
    public void testEqualsAndHashcode() {

        CountCampaignOffers countCampaignOffers1 = new CountCampaignOffers(12, 20, 8);

        assertEquals(countCampaignOffers1, countCampaignOffers1);
        assertEquals(countCampaignOffers1.hashCode(), countCampaignOffers1.hashCode());

        assertNotEquals(countCampaignOffers1, new Object());

        assertNotEquals(countCampaignOffers1, null);

        CountCampaignOffers countCampaignOffers2 = new CountCampaignOffers(12, 20, 8);
        assertEquals(countCampaignOffers1, countCampaignOffers2);
        assertEquals(countCampaignOffers1.hashCode(), countCampaignOffers2.hashCode());

        countCampaignOffers2 = new CountCampaignOffers(16, 22, 8);
        assertNotEquals(countCampaignOffers1, countCampaignOffers2);
        assertNotEquals(countCampaignOffers1.hashCode(), countCampaignOffers2.hashCode());

        countCampaignOffers2 = new CountCampaignOffers(16, 20, 18);
        assertNotEquals(countCampaignOffers1, countCampaignOffers2);
        assertNotEquals(countCampaignOffers1.hashCode(), countCampaignOffers2.hashCode());

    }

}
