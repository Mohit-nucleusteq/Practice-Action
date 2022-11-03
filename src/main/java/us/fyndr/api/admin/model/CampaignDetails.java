package us.fyndr.api.admin.model;

import java.time.Instant;
import java.util.Objects;

/**
 * CampaignDetails class stores result of campaign details query.
 *
 */
public class CampaignDetails {

    /**
     * The Campaign Id.
     */
    private Integer campaignId;

    /**
     * The Buisness Name.
     */
    private String buisnessName;

    /**
     * The Campaign Name.
     */
    private String campaignName;

    /**
     * The Buisness Type.
     */
    private String buisnessType;

    /**
     * The Campaign Type.
     */
    private String campaignType;

    /**
     * The Total Offer Sold Amount.
     */
    private Double totalOfferSoldAmount;

    /**
     * The End Date of Campaign.
     */
    private Instant endDate;

    /**
     * @return the buisnessName
     */
    public String getBuisnessName() {
        return buisnessName;
    }

    /**
     * @param buisnessName the buisnessName to set
     */
    public void setBuisnessName(final String buisnessName) {
        this.buisnessName = buisnessName;
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
     * @return the buisnessType
     */
    public String getBuisnessType() {
        return buisnessType;
    }

    /**
     * @param buisnessType the buisnessType to set
     */
    public void setBuisnessType(final String buisnessType) {
        this.buisnessType = buisnessType;
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
     * @return the totalOfferSoldAmount
     */
    public Double getTotalOfferSoldAmount() {
        return totalOfferSoldAmount;
    }

    /**
     * @param totalOfferSoldAmount the totalOfferSoldAmount to set
     */
    public void setTotalOfferSoldAmount(final Double totalOfferSoldAmount) {
        this.totalOfferSoldAmount = totalOfferSoldAmount;
    }

    /**
     * @return the endDate
     */
    public Instant getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(final Instant endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the campaignId
     */
    public Integer getCampaignId() {

        return campaignId;
    }

    /**
     * @param campaignId the campaignId to set
     */
    public void setCampaignId(final Integer campaignId) {
        this.campaignId = campaignId;
    }

    /**
     * the hashcode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(buisnessName, buisnessType, campaignId, campaignName, campaignType, endDate,
                totalOfferSoldAmount);
    }

    /**
     * the equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CampaignDetails)) {
            return false;
        }
        CampaignDetails other = (CampaignDetails) obj;
        return Objects.equals(buisnessName, other.buisnessName) && Objects.equals(buisnessType, other.buisnessType)
                && Objects.equals(campaignId, other.campaignId) && Objects.equals(campaignName, other.campaignName)
                && Objects.equals(campaignType, other.campaignType) && Objects.equals(endDate, other.endDate)
                && Objects.equals(totalOfferSoldAmount, other.totalOfferSoldAmount);
    }

    /**
     * the toString method.
     */
    @Override
    public String toString() {
        return "CampaignDetail [campaignId=" + campaignId + ", buisnessName=" + buisnessName + ", campaignName="
                + campaignName + ", buisnessType=" + buisnessType + ", campaignType=" + campaignType
                + ", totalOfferSoldAmount=" + totalOfferSoldAmount + ", endDate=" + endDate + "]";
    }

    /**
     * Parameterized constructor needed for storing data fetched from query.
     * @param campaignId stores objid of campaign table.
     * @param buisnessName stores business name .
     * @param campaignName stores campaign title.
     * @param buisnessType stores type of business.
     * @param campaignType stores type of campaign offer, coupon, brochure.
     * @param totalOfferSoldAmount store total sold amount of each campaign.
     * @param endDate stores end date of campaign.
     */
    public CampaignDetails(final Integer campaignId, final String buisnessName, final String campaignName,
            final String buisnessType, final String campaignType, final Double totalOfferSoldAmount,
            final Instant endDate) {
        this.campaignId = campaignId;
        this.buisnessName = buisnessName;
        this.campaignName = campaignName;
        this.buisnessType = buisnessType;
        this.campaignType = campaignType;
        this.totalOfferSoldAmount = totalOfferSoldAmount;
        this.endDate = endDate;

    }

}
