package us.fyndr.api.admin.dto;

import java.util.Objects;

import us.fyndr.api.admin.dbo.AccountStatus;

/**
 * BusinessDTO class contains the inforamtion of business user which will be
 * used in asyncronouslyUpdateProfile.
 *
 */
public class BusinessDTO {

    /**
     * The BizQrId is a business user's id.
     */
    private Long bizQRId;

    /**
     * The addressLine1 of business user.
     */
    private String addressLine1;

    /**
     * The addressLine2 of business user.
     */
    private String addressLine2;

    /**
     * The businessType of business user.
     */
    private String businessType;

    /**
     * The fyndrHandle of business user.
     */
    private String fyndrHandle;

    /**
     * The city of business user.
     */
    private String city;

    /**
     * The country of business user.
     */
    private String country;

    /**
     * The state of business user.
     */
    private String state;

    /**
     * The postalCode of business user.
     */
    private String postalCode;

    /**
     * The accountStatus of business user.
     */
    private AccountStatus accountStatus;

    /**
     * @return business user id
     */
    public Long getBizQRId() {
        return bizQRId;
    }

    /**
     * @param bizQRId setter method to set business user id
     */
    public void setBizQRId(final Long bizQRId) {
        this.bizQRId = bizQRId;
    }

    /**
     * @return address line 1 if business user.
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 setter method to set address line 2 of business user
     */
    public void setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return address line 2 of business user
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 setter method to set address line 2 of business user
     */
    public void setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return type of business user
     */
    public String getBusinessType() {
        return businessType;
    }

    /**
     * @param businessType setter method to set type of business user
     */
    public void setBusinessType(final String businessType) {
        this.businessType = businessType;
    }

    /**
     * @return fyndr handle of business user
     */
    public String getFyndrHandle() {
        return fyndrHandle;
    }

    /**
     * @param fyndrHandle setter method to set fyndr handle of business user
     */
    public void setFyndrHandle(final String fyndrHandle) {
        this.fyndrHandle = fyndrHandle;
    }

    /**
     * @return city of business user
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city setter method to set city of business user
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * @return country of business user
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country setter method to set country of business user
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * @return state of business user
     */
    public String getState() {
        return state;
    }

    /**
     * @param state setter method to set state business user
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * @return postalCode of business user
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode setter method to set postal code of business user
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return account status of business user
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus setter method to set account status of business user
     */
    public void setAccountStatus(final AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "BusinessDTO [bizQRId=" + bizQRId + ", addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2
                + ", businessType=" + businessType + ", fyndrHandle=" + fyndrHandle + ", city=" + city + ", country="
                + country + ", state=" + state + ", postalCode=" + postalCode + ", accountStatus=" + accountStatus
                + "]";
    }

    /**
     * The hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountStatus, addressLine1, addressLine2, bizQRId, businessType, city, country,
                fyndrHandle, postalCode, state);
    }

    /**
     * The equals method.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BusinessDTO)) {
            return false;
        }
        BusinessDTO other = (BusinessDTO) obj;
        return Objects.equals(accountStatus, other.accountStatus) && Objects.equals(addressLine1, other.addressLine1)
                && Objects.equals(addressLine2, other.addressLine2) && Objects.equals(bizQRId, other.bizQRId)
                && Objects.equals(businessType, other.businessType) && Objects.equals(city, other.city)
                && Objects.equals(country, other.country) && Objects.equals(fyndrHandle, other.fyndrHandle)
                && Objects.equals(postalCode, other.postalCode) && Objects.equals(state, other.state);
    }

}
