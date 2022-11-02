package us.fyndr.api.admin.dto;

import java.util.Objects;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import us.fyndr.api.admin.dbo.AccountStatus;

/**
 * UpdateStatusInDTO class contains User Id and Account Status of business user.
 *
 */
public class UpdateStatusInDTO {

    /**
     * The status of account.
     */
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;

    /**
     * @return status of account
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus - setter function to set the status of account
     */
    public void setAccountStatus(final AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "UpdateStatusInDTO [accountStatus=" + accountStatus + "]";
    }

    /**
     * The hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountStatus);
    }

    /**
     * The equals method.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof UpdateStatusInDTO)) {
            return false;
        }
        UpdateStatusInDTO other = (UpdateStatusInDTO) obj;
        return accountStatus == other.accountStatus;
    }

}
