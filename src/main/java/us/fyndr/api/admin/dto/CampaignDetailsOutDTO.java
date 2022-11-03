package us.fyndr.api.admin.dto;

import java.time.LocalDate;
import java.util.Objects;

import us.fyndr.api.admin.util.Constants;

/**
 * . CampaignDetailsOutDTO class shows detail of campaign
 */

public class CampaignDetailsOutDTO {

    /**
     * . The Object Id
     */
    private Long objId;

    /**
     * . The BusinessName
     */
    private String businessName;

    /**
     * . The Campaign Name
     */
    private String campaignName;

    /**
     * . The No Of Offers
     */
    private Integer activeOffers;

    /**
     * . The No Of TotalOffers
     */
    private Integer totalOffers;

    /**
     * . The IndustryType
     */
    private String industryType;

    /**
     * . The Campaign Type
     */
    private String campaignType;

    /**
     * . The OfferSold
     */
    private Integer offerSold;

    /**
     * The Total Offer Sold Amount.
     */
    private Double totalOfferSoldAmount = 0.0;

    /**
     * The End Date.
     */
    private LocalDate endDate;

    /**
     * The currency.
     */
    private String currency;

    /**
     * The currency Symbol.
     */
    private String currencySymbol;

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
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(final String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the campaignName
     */
    public String getCampaignName() {
        return campaignName;
    }

    /**
     * @param campaignName the campaignName to set
     */
    public void setCampaignName(final String campaignName) {
        this.campaignName = campaignName;
    }

    /**
     * @return the activeOffers
     */
    public Integer getActiveOffers() {
        return activeOffers;
    }

    /**
     * @param activeOffers the activeOffers to set
     */
    public void setActiveOffers(final Integer activeOffers) {
        this.activeOffers = activeOffers;
    }

    /**
     * @return the totalOffers
     */
    public Integer getTotalOffers() {
        return totalOffers;
    }

    /**
     * @param totalOffers the totalOffers to set
     */
    public void setTotalOffers(final Integer totalOffers) {
        this.totalOffers = totalOffers;
    }

    /**
     * @return the industryType
     */
    public String getIndustryType() {
        return industryType;
    }

    /**
     * @param industryType the industryType to set
     */
    public void setIndustryType(final String industryType) {
        this.industryType = industryType;
    }

    /**
     * @return the campaignType
     */
    public String getCampaignType() {
        return campaignType;
    }

    /**
     * @param campaignType the campaignType to set
     */
    public void setCampaignType(final String campaignType) {
        this.campaignType = campaignType;
    }

    /**
     * @return the offerSold
     */
    public Integer getOfferSold() {
        return offerSold;
    }

    /**
     * @param offerSold the offerSold to set
     */
    public void setOfferSold(final Integer offerSold) {
        this.offerSold = offerSold;
    }

    /**
     * @return the totalOfferSoldAmount
     */
    public Double getTotalOfferSoldAmount() {
        return totalOfferSoldAmount;
    }

    /**
     * @param totalOfferSoldAmount the totalOfferSoldAmount to set
     */
    public void setTotalOfferSoldAmount(final Double totalOfferSoldAmount) {
        if (Objects.nonNull(totalOfferSoldAmount)) {
            this.totalOfferSoldAmount = Math.round(totalOfferSoldAmount * Constants.HUNDRED) / Constants.HUNDRED;
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
     * the toString method.
     */
    @Override
    public String toString() {
        return "CampaignDetailsOutDTO [objId=" + objId + ", businessName=" + businessName + ", campaignName="
                + campaignName + ", activeOffers=" + activeOffers + ", totalOffers=" + totalOffers + ", industryType="
                + industryType + ", campaignType=" + campaignType + ", offerSold=" + offerSold
                + ", totalOfferSoldAmount=" + totalOfferSoldAmount + ", endDate=" + endDate + ", currency=" + currency
                + ", currencySymbol=" + currencySymbol + "]";
    }

    /**
     * the hashcode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(activeOffers, businessName, campaignName, campaignType, currency, currencySymbol, endDate,
                industryType, objId, offerSold, totalOfferSoldAmount, totalOffers);
    }

    /**
     * the equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CampaignDetailsOutDTO)) {
            return false;
        }
        CampaignDetailsOutDTO other = (CampaignDetailsOutDTO) obj;
        return Objects.equals(activeOffers, other.activeOffers) && Objects.equals(businessName, other.businessName)
                && Objects.equals(campaignName, other.campaignName) && Objects.equals(campaignType, other.campaignType)
                && Objects.equals(currency, other.currency) && Objects.equals(currencySymbol, other.currencySymbol)
                && Objects.equals(endDate, other.endDate) && Objects.equals(industryType, other.industryType)
                && Objects.equals(objId, other.objId) && Objects.equals(offerSold, other.offerSold)
                && Objects.equals(totalOfferSoldAmount, other.totalOfferSoldAmount)
                && Objects.equals(totalOffers, other.totalOffers);
    }

}
