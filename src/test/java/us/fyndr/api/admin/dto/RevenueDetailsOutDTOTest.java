package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class RevenueDetailsOutDTOTest {

    @Test
    public void testGetterAndSetter() {
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();

        assertNull(revenueDetailsOutDTO.getBusinessName());
        String businessName = "business name";
        revenueDetailsOutDTO.setBusinessName(businessName);
        assertEquals(businessName, revenueDetailsOutDTO.getBusinessName());

        assertEquals(0.0,revenueDetailsOutDTO.getOffers());
        Double offers = 45.65;
        revenueDetailsOutDTO.setOffers(offers);
        assertEquals(offers, revenueDetailsOutDTO.getOffers());
        
        assertEquals(0.0,revenueDetailsOutDTO.getInteraction());
        Double interaction = 54.56;
        revenueDetailsOutDTO.setInteraction(interaction);
        assertEquals(interaction, revenueDetailsOutDTO.getInteraction());
        
        assertEquals(0.0,revenueDetailsOutDTO.getPromo());
        Double promo = 14.656;
        revenueDetailsOutDTO.setPromo(promo);
        assertEquals(14.66, revenueDetailsOutDTO.getPromo());
        
        assertEquals(0.0,revenueDetailsOutDTO.getCatalog());
        Double catalogue = 45.656;
        revenueDetailsOutDTO.setCatalog(catalogue);
        assertEquals(45.66, revenueDetailsOutDTO.getCatalog());
        
        assertEquals(0.0,revenueDetailsOutDTO.getTotalRevenue());
        Double totalPayment = 454.656;
        revenueDetailsOutDTO.setTotalRevenue(totalPayment);
        assertEquals(454.66, revenueDetailsOutDTO.getTotalRevenue());
        
        assertNull(revenueDetailsOutDTO.getCurrency());
        String currency = "currency";
        revenueDetailsOutDTO.setCurrency(currency);
        assertEquals(currency, revenueDetailsOutDTO.getCurrency());
        
        assertNull(revenueDetailsOutDTO.getCurrencySymbol());
        String currencySymbol = "currencySymbol";
        revenueDetailsOutDTO.setCurrencySymbol(currencySymbol);
        assertEquals(currencySymbol, revenueDetailsOutDTO.getCurrencySymbol());

        assertNull(revenueDetailsOutDTO.getObjId());
        Long objId = 5L;
        revenueDetailsOutDTO.setObjId(objId);
        assertEquals(objId, revenueDetailsOutDTO.getObjId());

    }

    @Test
    public void testToString() {
        
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        String businessName = "business name";
        Double totalPayment = 89.903;
        String currency = "currency";
        String currencySymbol = "currencySymbol";
        Long objId = 5L;

        revenueDetailsOutDTO.setOffers(25.621);
        revenueDetailsOutDTO.setInteraction(55.120);
        revenueDetailsOutDTO.setCatalog(56.32);
        revenueDetailsOutDTO.setPromo(63.233);
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(totalPayment);
        revenueDetailsOutDTO.setCurrency(currency);
        revenueDetailsOutDTO.setCurrencySymbol(currencySymbol);
        revenueDetailsOutDTO.setObjId(objId);
                
        assertEquals("RevenueDetailsOutDTO [businessName=business name, objId=5, offers=25.62, "
                + "promo=63.23, catalog=56.32, interaction=55.12, totalRevenue=89.9, currency=currency, currencySymbol=currencySymbol]", 
                revenueDetailsOutDTO.toString());
    }
    
    @Test
    public void testEqualsAndHashcode() {
        
        String businessName = "business name";
        Double totalPayment = 89.903;
        String currency = "currency";
        String currencySymbol = "currencySymbol";
        Double offers = 56.369;
        Double interaction = 909.678;
        Double promo = 909.0;
        Double catalogue = 23.639;
        Long objId = 5L;
        
        RevenueDetailsOutDTO revenueDetailsOutDTO1 = buildRevenueDetailsOutDTO(businessName, objId, totalPayment, offers, promo, interaction, catalogue, currency, currencySymbol);
        
        assertEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO1);
        assertEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO1.hashCode());
        
        assertNotEquals(revenueDetailsOutDTO1, new Object());
        
        RevenueDetailsOutDTO revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, objId, totalPayment, offers, promo, interaction, catalogue, currency, currencySymbol);
        assertEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
        
        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName+ " ", objId, totalPayment, offers, promo, interaction, catalogue, currency, currencySymbol);
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());

        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, objId, 36.235, offers, promo, interaction, catalogue, currency, currencySymbol);
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
        
        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, objId, totalPayment, 96.32, promo, interaction, catalogue, currency, currencySymbol);
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
        
        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, objId, totalPayment, offers, 96.12, interaction, catalogue, currency, currencySymbol);
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
        
        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, objId, totalPayment, offers, promo, 87.36, catalogue, currency, currencySymbol);
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
        
        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, objId, totalPayment, offers, promo, interaction, 45.32, currency, currencySymbol);
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
        
        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, objId, totalPayment, offers, promo, interaction, catalogue, currency+" ", currencySymbol);
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
        
        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, objId, totalPayment, offers, promo, interaction, catalogue, currency, currencySymbol+" ");
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
        
        revenueDetailsOutDTO2 = buildRevenueDetailsOutDTO(businessName, 9L, totalPayment, offers, promo, interaction, catalogue, currency, currencySymbol+" ");
        assertNotEquals(revenueDetailsOutDTO1, revenueDetailsOutDTO2);
        assertNotEquals(revenueDetailsOutDTO1.hashCode(), revenueDetailsOutDTO2.hashCode());
    }

    private RevenueDetailsOutDTO buildRevenueDetailsOutDTO(String businessName, Long objId,
            Double totalPayment, Double offers, Double promo,
            Double interaction, Double catalogue, String currency,
            String currencySymbol) {
        
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setCatalog(catalogue);
        revenueDetailsOutDTO.setOffers(offers);
        revenueDetailsOutDTO.setInteraction(interaction);
        revenueDetailsOutDTO.setPromo(promo);
        revenueDetailsOutDTO.setTotalRevenue(totalPayment);
        revenueDetailsOutDTO.setCurrency(currency);
        revenueDetailsOutDTO.setCurrencySymbol(currencySymbol);
        revenueDetailsOutDTO.setObjId(objId);
        
        return revenueDetailsOutDTO;
    }


}
