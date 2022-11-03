package us.fyndr.api.admin.dto;

import java.util.List;
import java.util.Objects;

/**
 * PagedCampaignDetailOutDTO has campaign and pagination related details.
 *
 */
public class PagedCampaignDetailOutDTO {

    /**
     * . The last return true or false, the current page is last page or not
     */
    private boolean last;

    /**
     * . The revenue details list
     */
    private List<CampaignDetailsOutDTO> campaignDetails;

    /**
     * The total count of users.
     */
    private Long count;

    /**
     * @return the last
     */
    public boolean isLast() {
        return last;
    }

    /**
     * @param last the last to set
     */
    public void setLast(final boolean last) {
        this.last = last;
    }

    /**
     * @return the campaignDetails
     */
    public List<CampaignDetailsOutDTO> getCampaignDetails() {
        return campaignDetails;
    }

    /**
     * @param campaignDetails the campaignDetails to set
     */
    public void setCampaignDetails(final List<CampaignDetailsOutDTO> campaignDetails) {
        this.campaignDetails = campaignDetails;
    }

    /**
     * @return the count
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(final Long count) {
        this.count = count;
    }

    /**
     * the hashcode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(count, last, campaignDetails);
    }

    /**
     * the equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PagedCampaignDetailOutDTO)) {
            return false;
        }
        PagedCampaignDetailOutDTO other = (PagedCampaignDetailOutDTO) obj;
        return Objects.equals(count, other.count) && last == other.last
                && Objects.equals(campaignDetails, other.campaignDetails);
    }

    /**
     * the toString method.
     */
    @Override
    public String toString() {
        return "PagedCampaignDetailOutDTO [last=" + last + ", campaignDetails=" + campaignDetails + ", count=" + count
                + "]";
    }

}
