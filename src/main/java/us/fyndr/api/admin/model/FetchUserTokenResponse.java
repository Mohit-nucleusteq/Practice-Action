/**
 * @author Prerna Goyal
 *
 */
package us.fyndr.api.admin.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This class contains the model of token client service response.
 * Whenever TokenServiceClient fetchUserToken method is getting invoked then
 * this model response is expected.
 * */
public class FetchUserTokenResponse {

    /**
     * accessCode.
     */
    private String accessCode;

    /**
     * accessCodeExpiry.
     */
    private String accessCodeExpiry;

    /**
     * tokenUser.
     * user is passed from token service client
     */
    @JsonProperty(value = "user")
    private TokenUser tokenUser;

    /**
     * @return the accessCode
     */
    public String getAccessCode() {
        return accessCode;
    }

    /**
     * @param accessCode the accessCode to set
     */
    public void setAccessCode(final String accessCode) {
        this.accessCode = accessCode;
    }

    /**
     * @return the accessCodeExpiry
     */
    public String getAccessCodeExpiry() {
        return accessCodeExpiry;
    }

    /**
     * @param accessCodeExpiry the accessCodeExpiry to set
     */
    public void setAccessCodeExpiry(final String accessCodeExpiry) {
        this.accessCodeExpiry = accessCodeExpiry;
    }

    /**
     * @return the tokenUser
     */
    public TokenUser getTokenUser() {
        return tokenUser;
    }

    /**
     * @param tokenUser the tokenUser to set
     */
    public void setTokenUser(final TokenUser tokenUser) {
        this.tokenUser = tokenUser;
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "FetchUserTokenResponse [accessCode=" + accessCode + ", accessCodeExpiry="
                + accessCodeExpiry + ", tokenUser=" + tokenUser + "]";
    }

    /**
     * The hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(accessCode, accessCodeExpiry, tokenUser);
    }

    /**
     * The equals method.
     * @param obj
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FetchUserTokenResponse)) {
            return false;
        }
        FetchUserTokenResponse other = (FetchUserTokenResponse) obj;
        return Objects.equals(accessCode, other.accessCode) && Objects.equals(accessCodeExpiry, other.accessCodeExpiry)
                && Objects.equals(tokenUser, other.tokenUser);
    }

}
