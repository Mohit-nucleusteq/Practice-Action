package us.fyndr.api.admin.dto;

import java.util.Objects;

/**
 * @author saksh
 * The CampaignStatisticsOutDTO Class presents the details of campaign statistics.
 */
public class CampaignStatisticsOutDTO {

    /**
     * The active campaign count.
     */
    private Long active;

    /**
     * The online campaign count.
     */
    private Long online;

    /**
     * The instore campaign count.
     */
    private Long instore;

    /**
     * The online and instore campaign count.
     */
    private Long all;

    /**
     * @return the activeCampaign
     */
    public Long getActive() {
        return active;
    }

    /**
     * @param active sets the active field
     */
    public void setActive(final Long active) {
        this.active = active;
    }

    /**
     * @return Long - count of the campaign whose goal is 'online'
     */
    public Long getOnline() {
        return online;
    }

    /**
     * @param online sets the 'online' field
     */
    public void setOnline(final Long online) {
        this.online = online;
    }

    /**
     * @return Long - count of the campaign whose goal is 'instore'
     */
    public Long getInstore() {
        return instore;
    }

    /**
     * @param instore sets the 'instore' field.
     */
    public void setInstore(final Long instore) {
        this.instore = instore;
    }

    /**
     * @return Long - count of the campaign whose goal is 'all'
     */
    public Long getAll() {
        return all;
    }

    /**
     * @param all sets the 'all' field.
     */
    public void setAll(final Long all) {
        this.all = all;
    }

    /**
     * hashCode() method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(active, instore, all,
                online);
    }

    /**
     * Equals() method for CampaignStatisticsOutDTO Class.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CampaignStatisticsOutDTO)) {
            return false;
        }
        CampaignStatisticsOutDTO other = (CampaignStatisticsOutDTO) obj;
        return Objects.equals(active, other.active)
                && Objects.equals(instore, other.instore)
                && Objects.equals(all, other.all)
                && Objects.equals(online, other.online);
    }

    /**
     * toString() method for CampaignStatisticsOutDTO fields.
     */
    @Override
    public String toString() {
        return "CampaignStatisticsOutDTO [active=" + active + ", online="
                + online + ", instore=" + instore
                + ", all=" + all + "]";
    }
}
