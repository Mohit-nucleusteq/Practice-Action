package us.fyndr.api.admin.model;

import java.util.Objects;

/**
 * CountCampaignOffers class stores result of count of campaign offers.
 */
public class CountCampaignOffers {

    /**
     * The Active Offer.
     */
    private Integer activeOffer;

    /**
     * The Total Offer.
     */
    private Integer totalOffer;

    /**
     * The OfferSold.
     */
    private Integer offerSold;

    /**
     * @return the activeOffer
     */
    public Integer getActiveOffer() {
        return activeOffer;
    }

    /**
     * @param activeOffer the activeOffer to set
     */
    public void setActiveOffer(final Integer activeOffer) {
        this.activeOffer = activeOffer;
    }

    /**
     * @return the totalOffer
     */
    public Integer getTotalOffer() {
        return totalOffer;
    }

    /**
     * @param totalOffer the totalOffer to set
     */
    public void setTotalOffer(final Integer totalOffer) {
        this.totalOffer = totalOffer;
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
     * the hash code method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(activeOffer, offerSold, totalOffer);
    }

    /**
     * the hash code equals.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CountCampaignOffers)) {
            return false;
        }
        CountCampaignOffers other = (CountCampaignOffers) obj;
        return Objects.equals(activeOffer, other.activeOffer) && Objects.equals(offerSold, other.offerSold)
                && Objects.equals(totalOffer, other.totalOffer);
    }

    /**
     * the toString method.
     */
    @Override
    public String toString() {
        return "CountCampaignOffers [activeOffer=" + activeOffer + ", totalOffer=" + totalOffer + ", offerSold="
                + offerSold + "]";
    }

    /**
     * Parameterized constructor needed for storing data fetched from query.
     * @param activeOffer stores total number of active offer.
     * @param totalOffer  stores total offers for particular campaign id.
     * @param offerSold   stores total number of offer sold.
     */
    public CountCampaignOffers(final Integer activeOffer, final Integer totalOffer, final Integer offerSold) {
        this.totalOffer = totalOffer;
        this.offerSold = offerSold;
        this.activeOffer = activeOffer;
    }

}
