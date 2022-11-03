package us.fyndr.api.admin.dto;

import java.util.Objects;

import us.fyndr.api.admin.util.Constants;

/**
 * The RevenueStatisticsOutDTO Class presents the details of revenue statistics.
 *
 */
public class RevenueStatisticsOutDTO {

    /**
     * The total payment processed.
     */
    private Double totalRevenue;

    /**
     * The total offer Payments.
     */
    private Double offerRevenue;

    /**
     * The total catalogue Payments.
     */
    private Double catalogueRevenue;

    /**
     * The total promotional Payments.
     */
    private Double promotionalRevenue;

    /**
     * The total interaction payments.
     */
    private Double interactionRevenue;

    /**
     * The type of currency.
     */
    private String currency;

    /**
     * The type of currency symbol.
     */
    private String currencySymbol;

    /**
     * @return the total Payments processed
     */
    public Double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * @param totalRevenue setter method to set total revenue
     */
    public void setTotalRevenue(final Double totalRevenue) {
        this.totalRevenue = getPrecision(totalRevenue);
    }

    /**
     * @return the total offer Payments
     */
    public Double getOfferRevenue() {
        return offerRevenue;
    }

    /**
     * @param offerRevenue setter method to set total offer revenue
     */
    public void setOfferRevenue(final Double offerRevenue) {
        this.offerRevenue = getPrecision(offerRevenue);
    }

    /**
     * @return the total catalogue Payments
     */
    public Double getCatalogueRevenue() {
        return catalogueRevenue;
    }

    /**
     * @param catalogueRevenue setter method to set total catalogue revenue
     */
    public void setCatalogueRevenue(final Double catalogueRevenue) {
        this.catalogueRevenue = getPrecision(catalogueRevenue);
    }

    /**
     * @return the total promotional Payments
     */
    public Double getPromotionalRevenue() {
        return promotionalRevenue;
    }

    /**
     * @param promotionalRevenue setter method to set total promotional revenue
     */
    public void setPromotionalRevenue(final Double promotionalRevenue) {
        this.promotionalRevenue = getPrecision(promotionalRevenue);
    }

    /**
     * @return the total interaction Payments
     */
    public Double getInteractionRevenue() {
        return interactionRevenue;
    }

    /**
     * @param interactionRevenue setter method to set total interaction revenue
     */
    public void setInteractionRevenue(final Double interactionRevenue) {
        this.interactionRevenue = getPrecision(interactionRevenue);
    }

    /**
     * @return the type of currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the setter method to set type of currency
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * @return the currency Symbol
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * @param currencySymbol the setter method to set symbol of currency
     */
    public void setCurrencySymbol(final String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    /**
     * @param revenue
     * @return roundedRevenueValue value returned of revenue by 2
     */
    private Double getPrecision(final Double revenue) {
        Double roundedRevenueValue = Math.round(revenue * Constants.HUNDRED) / Constants.HUNDRED;
        return roundedRevenueValue;
    }

    /**
     * The hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(catalogueRevenue, currency, currencySymbol, interactionRevenue, offerRevenue,
                promotionalRevenue, totalRevenue);
    }

    /**
     * The equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RevenueStatisticsOutDTO)) {
            return false;
        }
        RevenueStatisticsOutDTO other = (RevenueStatisticsOutDTO) obj;
        return Objects.equals(catalogueRevenue, other.catalogueRevenue) && Objects.equals(currency, other.currency)
                && Objects.equals(currencySymbol, other.currencySymbol)
                && Objects.equals(interactionRevenue, other.interactionRevenue)
                && Objects.equals(offerRevenue, other.offerRevenue)
                && Objects.equals(promotionalRevenue, other.promotionalRevenue)
                && Objects.equals(totalRevenue, other.totalRevenue);
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "RevenueStatisticsOutDTO [totalRevenue=" + totalRevenue + ", offerRevenue=" + offerRevenue
                + ", catalogueRevenue=" + catalogueRevenue + ", promotionalRevenue=" + promotionalRevenue
                + ", interactionRevenue=" + interactionRevenue + ", currency=" + currency + ", currencySymbol="
                + currencySymbol + "]";
    }

}
