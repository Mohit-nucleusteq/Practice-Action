package us.fyndr.api.admin.dto;

import java.util.Objects;

import us.fyndr.api.admin.dbo.AccountStatus;

/**
 * UpdateStatusOutDTO class contains the details after updating successfully
 * account status.
 *
 */
public class UpdateStatusOutDTO {
    /**
     * The Success of account status.
     */
    private Boolean success;

    /**
     * The status of account.
     */
    private AccountStatus accountStatus;

    /**
     * The Message after completing updation.
     */
    private String message;

    /**
     * @return message after successfully updating account status
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message - Setter function to set message after successfully updating
     *                account status
     */
    public void setMessage(final String message) {
        this.message = message;
    }

    /**
     * @return The status of account
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus - Setter function to set status of account
     */
    public void setAccountStatus(final AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * @return success of account status
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * @param success - Setter function to set account status
     */
    public void setSuccess(final Boolean success) {
        this.success = success;
    }

    /**
     * The toString Method.
     */
    @Override
    public String toString() {
        return "UpdateStatusOutDTO [success=" + success + ", accountStatus=" + accountStatus + ", message=" + message
                + "]";
    }

    /**
     * The hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountStatus, message, success);
    }

    /**
     * The equals method.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UpdateStatusOutDTO)) {
            return false;
        }
        UpdateStatusOutDTO other = (UpdateStatusOutDTO) obj;
        return accountStatus == other.accountStatus && Objects.equals(message, other.message)
                && Objects.equals(success, other.success);
    }

}
