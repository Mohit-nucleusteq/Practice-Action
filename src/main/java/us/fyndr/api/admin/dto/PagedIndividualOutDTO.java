package us.fyndr.api.admin.dto;

import java.util.List;
import java.util.Objects;

/**
 * . PagedIndividualOutDTO present the details of pages and list of individual
 * search
 */
public class PagedIndividualOutDTO {

    /**
     * . The last return true or false, the current page is last page or not
     */
    private boolean last;

    /**
     * . The individuals is a list of searched user
     */
    private List<IndividualOutDTO> users;

    /**
     * The total count of users.
     */
    private Long count;

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
     * @return true or false if page is last
     */
    public boolean isLast() {
        return last;
    }

    /**
     * @param last true or false
     */
    public void setLast(final boolean last) {
        this.last = last;
    }

    /**
     * @return individuals search list
     */
    public List<IndividualOutDTO> getUsers() {
        return users;
    }

    /**
     * @param individuals sets individual search list
     */
    public void setUsers(final List<IndividualOutDTO> individuals) {
        this.users = individuals;
    }

    /**
     * . The hashcode method
     */
    @Override
    public final int hashCode() {
        return Objects.hash(users, last, count);
    }

    /**
     * . the equals method
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PagedIndividualOutDTO)) {
            return false;
        }
        PagedIndividualOutDTO other = (PagedIndividualOutDTO) obj;
        return Objects.equals(users, other.users) && last == other.last && Objects.equals(count, other.count);
    }

    /**
     * . the toString method
     */
    @Override
    public String toString() {
        return "PagedIndividualOutDTO [last=" + last + ", users=" + users + ", count=" + count + "]";
    }
}
