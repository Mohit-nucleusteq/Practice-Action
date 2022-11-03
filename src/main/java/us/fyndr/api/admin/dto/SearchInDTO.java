package us.fyndr.api.admin.dto;

import java.util.List;
import java.util.Objects;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.UserType;

/**
 * . The purpose of the SearchInDTO is to search according to the data we are
 * passing
 */
public class SearchInDTO {

    /**
     * . The status of a user
     */
    private List<AccountStatus> userStatus;

    /**
     * . The type of a user
     */
    private List<UserType> userType;

    /**
     * . The text sent by admin
     */
    private String text;

    /**
     * The country of a user.
     */
    private String country = "US";

    /**
     * @return the status of a user
     */
    public List<AccountStatus> getUserStatus() {
        return userStatus;
    }

    /**
     * @param userStatus sets status of a user
     */
    public void setUserStatus(final List<AccountStatus> userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * @return type of user
     */
    public List<UserType> getUserType() {
        return userType;
    }

    /**
     * @param userType sets the type of user
     */
    public void setUserType(final List<UserType> userType) {
        this.userType = userType;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text sets the text sent by admin
     */
    public void setText(final String text) {
        this.text = text;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country set the country of user
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * The Hash Code method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(country, text, userStatus, userType);
    }

    /**
     * The equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SearchInDTO)) {
            return false;
        }
        SearchInDTO other = (SearchInDTO) obj;
        return Objects.equals(country, other.country) && Objects.equals(text, other.text)
                && Objects.equals(userStatus, other.userStatus) && Objects.equals(userType, other.userType);
    }

    /**
     * . the tostring method
     */
    @Override
    public String toString() {
        return "SearchInDTO [userStatus=" + userStatus + ", userType=" + userType + ", text=" + text + ", country="
                + country + "]";
    }
}
