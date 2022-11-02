package us.fyndr.api.admin.model;

import java.util.Objects;

/**
 * @author Prerna Goyal
 *
 */
public class FetchUserTokenRequest {

    /**
     * email for token request.
     */
    private String email;

    /**
     * constructor.
     * @param email the email of the token request
     */
    public FetchUserTokenRequest(final String email) {
        this.email = email;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }
    /**
     * The hashcode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(email);

    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "FetchUserTokenRequest [email=" + email + "]";
    }

    /**
     * The equals method.
     */
    @Override
    public boolean equals(final Object obj) {

        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FetchUserTokenRequest)) {
            return false;
        }
        FetchUserTokenRequest other = (FetchUserTokenRequest) obj;
        return Objects.equals(email, other.email);
    }
}
