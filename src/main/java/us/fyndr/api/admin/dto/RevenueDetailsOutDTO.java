package us.fyndr.api.admin.dto;

import java.util.Objects;

import us.fyndr.api.admin.util.Constants;


/**
 * .The RevenueDetailsOutDTO stores the revenue details.
 * Contains businessName, different invoice channels revenues
 * and total payment received by a business
 */
public class RevenueDetailsOutDTO {

    /**
     * businessName.
     */
    private String businessName;

    /**
     * objId.
     */
    private Long objId;

    /**
     * offers.
     * Default set to 0.0
     */
    private Double offers = 0.0;

    /**
     * promotional.
     * Default set to 0.0
     */
    private Double promo = 0.0;

    /**
     * catalog.
     * Default set to 0.0
     */
    private Double catalog = 0.0;

    /**
     * interaction.
     * Default set to 0.0
     */
    private Double interaction = 0.0;

    /**
     * totalRevenue.
     */
    private Double totalRevenue = 0.0;

    /**
     * The currency.
     */
    private String currency;

    /**
     * The currency symbol.
     */
    private String currencySymbol;

    /**
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(final String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the objId
     */
    public Long getObjId() {
        return objId;
    }

    /**
     * @param objId the objId to set
     */
    public void setObjId(final Long objId) {
        this.objId = objId;
    }

    /**
     * @return the offers
     */
    public Double getOffers() {
        return offers;
    }

    /**
     * @param offers the offers to set
     */
    public void setOffers(final Double offers) {
        if (Objects.nonNull(offers)) {
            this.offers = Math.round(offers * Constants.HUNDRED) / Constants.HUNDRED;
        }
    }

    /**
     * @return the promo
     */
    public Double getPromo() {
        return promo;
    }

    /**
     * @param promo the promo to set
     */
    public void setPromo(final Double promo) {
        if (Objects.nonNull(promo)) {
            this.promo = Math.round(promo * Constants.HUNDRED) / Constants.HUNDRED;
        }
    }

    /**
     * @return the catalogue
     */
    public Double getCatalog() {
        return catalog;
    }

    /**
     * @param catalog the catalog to set
     */
    public void setCatalog(final Double catalog) {
        if (Objects.nonNull(catalog)) {
            this.catalog = Math.round(catalog * Constants.HUNDRED) / Constants.HUNDRED;
        }
    }

    /**
     * @return the interaction
     */
    public Double getInteraction() {
        return interaction;
    }

    /**
     * @param interaction the interaction to set
     */
    public void setInteraction(final Double interaction) {
        if (Objects.nonNull(interaction)) {
            this.interaction = Math.round(interaction * Constants.HUNDRED) / Constants.HUNDRED;
        }
    }
    /**
     * @return the totalPayment
     */
    public Double getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * @param totalRevenue the totalPayment to set
     */
    public void setTotalRevenue(final Double totalRevenue) {
        if (Objects.nonNull(totalRevenue)) {
            this.totalRevenue = Math.round(totalRevenue * Constants.HUNDRED) / Constants.HUNDRED;
        }
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * @return the currencySymbol
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * @param currencySymbol the currencySymbol to set
     */
    public void setCurrencySymbol(final String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    /**
     * hashCode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(objId, businessName, catalog, currency, currencySymbol,
                interaction, offers, promo, totalRevenue);
    }

    /**
     * equals.
     * */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RevenueDetailsOutDTO)) {
            return false;
        }
        RevenueDetailsOutDTO other = (RevenueDetailsOutDTO) obj;
        return Objects.equals(objId, other.objId)
                && Objects.equals(businessName, other.businessName)
                && Objects.equals(catalog, other.catalog)
                && Objects.equals(currency, other.currency)
                && Objects.equals(currencySymbol, other.currencySymbol)
                && Objects.equals(interaction, other.interaction)
                && Objects.equals(offers, other.offers)
                && Objects.equals(promo, other.promo)
                && Objects.equals(totalRevenue, other.totalRevenue);
    }

    /**
     * toString.
     */
    @Override
    public String toString() {
        return "RevenueDetailsOutDTO [businessName=" + businessName
                + ", objId=" + objId + ", offers=" + offers
                + ", promo=" + promo + ", catalog=" + catalog + ", interaction="
                + interaction + ", totalRevenue=" + totalRevenue + ", currency="
                + currency + ", currencySymbol=" + currencySymbol + "]";
    }
}
