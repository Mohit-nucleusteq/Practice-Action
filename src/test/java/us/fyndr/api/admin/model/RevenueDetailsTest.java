package us.fyndr.api.admin.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

class RevenueDetailsTest {

    @Test
    public void testGetterAndSetter() {

        String businessName = "business name";
        String channel = "channel";
        Double payment = 25.00;

        
        RevenueDetails revenueDetails = new RevenueDetails(payment, businessName, channel);
        
        String businessName1 = "business name1";
        revenueDetails.setBusinessName(businessName1);
        assertEquals(businessName1, revenueDetails.getBusinessName());
        
        String channel1 = "channel1";
        revenueDetails.setChannel(channel1);
        assertEquals(channel1, revenueDetails.getChannel());
        
        Double payment1 = 21.00;
        revenueDetails.setPayment(payment1);;
        assertEquals(payment1, revenueDetails.getPayment());

    }

    @Test
    public void testToString() {

        String businessName = "business name";
        String channel = "channel";
        double payment = 25.51;

        
        RevenueDetails revenueDetails = new RevenueDetails(payment, businessName, channel);
        
        assertEquals("RevenueDetails [payment=25.51, "
                + "businessName=business name, channel=channel]", revenueDetails.toString());
    }

    @Test
    public void testEqualsAndHashcode() {
        
        String businessName = "business name";
        String channel = "channel";
        Double payment = 25.00;

        RevenueDetails revenueDetails1 = new RevenueDetails(payment, businessName, channel);

        assertEquals(revenueDetails1, revenueDetails1);
        assertEquals(revenueDetails1.hashCode(), revenueDetails1.hashCode());
        
        assertNotEquals(revenueDetails1, new Object());
        
        RevenueDetails revenueDetails2 = new RevenueDetails(payment, businessName, channel);
        assertEquals(revenueDetails1, revenueDetails2);
        assertEquals(revenueDetails1.hashCode(), revenueDetails2.hashCode());
        
        Double payment1 = 25.60;
        
        revenueDetails2 = new RevenueDetails(payment1, businessName, channel);
        assertNotEquals(revenueDetails1, revenueDetails2);
        assertNotEquals(revenueDetails1.hashCode(), revenueDetails2.hashCode());

        String businessName1 = "business name 1";

        revenueDetails2 = new RevenueDetails(payment, businessName1, channel);
        assertNotEquals(revenueDetails1, revenueDetails2);
        assertNotEquals(revenueDetails1.hashCode(), revenueDetails2.hashCode());

        String channel1 = "channel1";

        revenueDetails2 = new RevenueDetails(payment, businessName, channel1);
        assertNotEquals(revenueDetails1, revenueDetails2);
        assertNotEquals(revenueDetails1.hashCode(), revenueDetails2.hashCode());

       
    }

}
