package us.fyndr.api.admin.dto;

import java.util.Objects;

public class UserStatisticsOutDTO {

    /**
     * . The total number of user
     */
    private Long user;
    /**
     * . The total number of customer
     */
    private Long customer;
    /**
     * . The total number of merchant
     */
    private Long merchant;

    /**
     * @return the user
     */
    public Long getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(final Long user) {
        this.user = user;
    }

    /**
     * @return the customer
     */
    public Long getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(final Long customer) {
        this.customer = customer;
    }

    /**
     * @return the merchant
     */
    public Long getMerchant() {
        return merchant;
    }

    /**
     * @param merchant the merchant to set
     */
    public void setMerchant(final Long merchant) {
        this.merchant = merchant;
    }

    /**
     * . The hashcode method
     */
    @Override
    public int hashCode() {
        return Objects.hash(customer, merchant, user);
    }

    /**
     * . The equals method
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UserStatisticsOutDTO)) {
            return false;
        }
        UserStatisticsOutDTO other = (UserStatisticsOutDTO) obj;
        return Objects.equals(customer, other.customer) && Objects.equals(merchant, other.merchant)
                && Objects.equals(user, other.user);
    }

    /**
     * . The to string method
     */
    @Override
    public String toString() {
        return "UserStatisticsOutDTO [user=" + user + ", customer=" + customer + ", merchant=" + merchant + "]";
    }

}
