package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.util.Constants;

public class RevenueStatisticsOutDTOTest {

    @Test
    public void testGetterAndSetter() {

        RevenueStatisticsOutDTO revenueStatisticsOutDTO = new RevenueStatisticsOutDTO();

        assertNull(revenueStatisticsOutDTO.getCatalogueRevenue());
        Double catalogue = 125.5;
        revenueStatisticsOutDTO.setCatalogueRevenue(catalogue);
        catalogue = getPrecisionValue(catalogue);
        assertEquals(catalogue, revenueStatisticsOutDTO.getCatalogueRevenue());

        assertNull(revenueStatisticsOutDTO.getInteractionRevenue());
        Double interactions = 155.55023;
        revenueStatisticsOutDTO.setInteractionRevenue(interactions);
        interactions = getPrecisionValue(interactions);
        assertEquals(interactions, revenueStatisticsOutDTO.getInteractionRevenue());

        assertNull(revenueStatisticsOutDTO.getOfferRevenue());
        Double offer = 165.53424;
        revenueStatisticsOutDTO.setOfferRevenue(offer);
        offer = getPrecisionValue(offer);
        assertEquals(offer, revenueStatisticsOutDTO.getOfferRevenue());

        assertNull(revenueStatisticsOutDTO.getPromotionalRevenue());
        Double promotional = 453.1999977;
        revenueStatisticsOutDTO.setPromotionalRevenue(promotional);
        promotional = getPrecisionValue(promotional);
        assertEquals(promotional, revenueStatisticsOutDTO.getPromotionalRevenue());

        assertNull(revenueStatisticsOutDTO.getTotalRevenue());
        Double total = 4453.5;
        revenueStatisticsOutDTO.setTotalRevenue(total);
        total = getPrecisionValue(total);
        assertEquals(total, revenueStatisticsOutDTO.getTotalRevenue());

        assertNull(revenueStatisticsOutDTO.getCurrency());
        String currency = "currency";
        revenueStatisticsOutDTO.setCurrency(currency);
        assertEquals(currency, revenueStatisticsOutDTO.getCurrency());

        assertNull(revenueStatisticsOutDTO.getCurrencySymbol());
        String currencySymbol = "currencySymbol";
        revenueStatisticsOutDTO.setCurrencySymbol(currencySymbol);
        assertEquals(currencySymbol, revenueStatisticsOutDTO.getCurrencySymbol());

    }

    private Double getPrecisionValue(Double revenue) {
        revenue = Math.round(revenue * Constants.HUNDRED) / Constants.HUNDRED;
        return revenue;
    }

    @Test
    public void testToString() {

        RevenueStatisticsOutDTO revenueStatisticsOutDTO = new RevenueStatisticsOutDTO();

        Double catalogue = 125.5342;
        revenueStatisticsOutDTO.setCatalogueRevenue(catalogue);
        catalogue = getPrecisionValue(catalogue);

        Double interactions = 155.5;
        revenueStatisticsOutDTO.setInteractionRevenue(interactions);
        interactions = getPrecisionValue(interactions);

        Double offer = 165.5;
        revenueStatisticsOutDTO.setOfferRevenue(offer);
        offer = getPrecisionValue(offer);

        Double promotional = 453.5;
        revenueStatisticsOutDTO.setPromotionalRevenue(promotional);
        promotional = getPrecisionValue(promotional);

        Double total = 4453.5;
        revenueStatisticsOutDTO.setTotalRevenue(total);
        total = getPrecisionValue(total);

        String currency = "currency";
        revenueStatisticsOutDTO.setCurrency(currency);

        String currencySymbol = "currencySymbol";
        revenueStatisticsOutDTO.setCurrencySymbol(currencySymbol);

        assertEquals(
                "RevenueStatisticsOutDTO [totalRevenue=" + total + ", offerRevenue=" + offer + ", catalogueRevenue="
                        + catalogue + "," + " promotionalRevenue=" + promotional + ", interactionRevenue="
                        + interactions + ", currency=" + currency + ", currencySymbol=" + currencySymbol + "]",
                revenueStatisticsOutDTO.toString());
    }

    @Test
    public void testEqualsAndHashcode() {

        Double catalogue = 125.545;
        Double interactions = 155.5244;
        Double offer = 165.52425;
        Double promotional = 453.52425;
        Double total = 4453.5241;
        String currency = "currency";
        String currencySymbol = "currencySymbol";

        RevenueStatisticsOutDTO revenueStatisticsOutDTO1 = buildRevenueStatisticsOutDTO(catalogue, interactions, offer,
                promotional, total, currency, currencySymbol);

        assertEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO1);
        assertEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO1.hashCode());

        assertNotEquals(revenueStatisticsOutDTO1, new Object());

        RevenueStatisticsOutDTO revenueStatisticsOutDTO2 = buildRevenueStatisticsOutDTO(catalogue, interactions, offer,
                promotional, total, currency, currencySymbol);
        assertEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());

        revenueStatisticsOutDTO2 = buildRevenueStatisticsOutDTO(46.67, interactions, offer, promotional, total,
                currency, currencySymbol);
        assertNotEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertNotEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());

        revenueStatisticsOutDTO2 = buildRevenueStatisticsOutDTO(catalogue, 566.75, offer, promotional, total, currency,
                currencySymbol);
        assertNotEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertNotEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());

        revenueStatisticsOutDTO2 = buildRevenueStatisticsOutDTO(catalogue, interactions, 567.32, promotional, total,
                currency, currencySymbol);
        assertNotEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertNotEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());

        revenueStatisticsOutDTO2 = buildRevenueStatisticsOutDTO(catalogue, interactions, offer, 533.8, total, currency,
                currencySymbol);
        assertNotEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertNotEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());

        revenueStatisticsOutDTO2 = buildRevenueStatisticsOutDTO(catalogue, interactions, offer, promotional, 3425.5,
                currency, currencySymbol);
        assertNotEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertNotEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());

        revenueStatisticsOutDTO2 = buildRevenueStatisticsOutDTO(catalogue, interactions, offer, promotional, 3425.5,
                "currencyy", currencySymbol);
        assertNotEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertNotEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());

        revenueStatisticsOutDTO2 = buildRevenueStatisticsOutDTO(catalogue, interactions, offer, promotional, 3425.5,
                currency, "currencySymbol");
        assertNotEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertNotEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());

        revenueStatisticsOutDTO1 = new RevenueStatisticsOutDTO();
        revenueStatisticsOutDTO2 = new RevenueStatisticsOutDTO();
        assertEquals(revenueStatisticsOutDTO1, revenueStatisticsOutDTO2);
        assertEquals(revenueStatisticsOutDTO1.hashCode(), revenueStatisticsOutDTO2.hashCode());
    }

    private RevenueStatisticsOutDTO buildRevenueStatisticsOutDTO(Double catalogue, Double interactions, Double offer,
            Double promotional, Double total, String currency, String currencySymbol) {

        RevenueStatisticsOutDTO revenueStatisticsOutDTO = new RevenueStatisticsOutDTO();

        revenueStatisticsOutDTO.setCatalogueRevenue(catalogue);
        revenueStatisticsOutDTO.setInteractionRevenue(interactions);
        revenueStatisticsOutDTO.setOfferRevenue(offer);
        revenueStatisticsOutDTO.setPromotionalRevenue(promotional);
        revenueStatisticsOutDTO.setTotalRevenue(total);
        revenueStatisticsOutDTO.setCurrency(currency);
        revenueStatisticsOutDTO.setCurrencySymbol(currencySymbol);

        return revenueStatisticsOutDTO;
    }
}
