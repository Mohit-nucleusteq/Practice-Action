package us.fyndr.api.admin.dto;

import java.util.List;
import java.util.Objects;

public class PagedRevenueDetailsOutDTO {

    /**
     * . The last return true or false, the current page is last page or not
     */
    private boolean last;

    /**
     * . The revenue details list
     *
     */
    private List<RevenueDetailsOutDTO> revenueDetails;

    /**
     * The total count of businesses for which paid invoice are available.
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
     * @return the revenueDetails
     */
    public List<RevenueDetailsOutDTO> getRevenueDetails() {
        return revenueDetails;
    }

    /**
     * @param revenueDetails the revenueDetails to set
     */
    public void setRevenueDetails(final List<RevenueDetailsOutDTO> revenueDetails) {
        this.revenueDetails = revenueDetails;
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
     * toString.
     * */
    @Override
    public String toString() {
        return "PagedRevenueDetailsOutDTO [last=" + last + ", revenueDetails="
                + revenueDetails + ", count=" + count + "]";
    }

    /**
     * hashCode.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(count, last, revenueDetails);
    }

    /**
     * equals.
     * */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj  instanceof PagedRevenueDetailsOutDTO)) {
            return false;
        }
        PagedRevenueDetailsOutDTO other = (PagedRevenueDetailsOutDTO) obj;
        return Objects.equals(count, other.count) && last == other.last
                && Objects.equals(revenueDetails, other.revenueDetails);
    }

}
