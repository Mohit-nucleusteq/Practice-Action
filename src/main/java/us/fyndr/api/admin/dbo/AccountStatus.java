package us.fyndr.api.admin.dbo;

/**
 * . The purpose of this enum is to select the correct value in the account
 * status field
 */
public enum AccountStatus {
    /**
     * . {@value = ACTIVE, INACTIVE, SUSPENDED, DELETED} there are four type of
     * account status and account status must be one of them
     */
    ACTIVE, INACTIVE, SUSPENDED, DELETED
}
