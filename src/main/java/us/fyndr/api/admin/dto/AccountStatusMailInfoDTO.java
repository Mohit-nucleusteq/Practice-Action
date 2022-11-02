package us.fyndr.api.admin.dto;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import us.fyndr.api.admin.dbo.AccountStatus;

/**
 * AccountStatusMailInfoDTO class contains details which will set to the data of
 * AccountStatusEmailDTO.
 *
 */
public class AccountStatusMailInfoDTO {

    /**
     * The first name of the user.
     */
    @SerializedName(value = "first_name")
    private String firstName;

    /**
     * The business name.
     */
    @SerializedName(value = "business_name")
    private String businessName;

    /**
     * The AccountStatus defines the status of user's account.
     */
    @SerializedName(value = "account_status")
    private AccountStatus accountStatus;

    /**
     * The ImgBaseUrl is url for business.
     */
    @SerializedName(value = "img_baseurl")
    private String imgBaseUrl;

    /**
     * The Fyndr phone number.
     */
    @SerializedName(value = "fyndr_biz_phone")
    private String fyndrBusinessPhoneNo;

    /**
     * The Fyndr business website.
     */
    @SerializedName(value = "biz_website")
    private String fyndrBusinessWebsite;

    /**
     * The Fyndr's address.
     */
    @SerializedName(value = "fyndr_address")
    private String fyndrAddress;

    /**
     * @return FirstName of User
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName - Setter function for set first name of business user
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return Business Name of business user
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName - Setter function for set business name of business user
     */
    public void setBusinessName(final String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return accountStatus of a user
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus - Setter function for set account status of a user
     */
    public void setAccountStatus(final AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * @return the imgBaseUrl
     */
    public String getImgBaseUrl() {
        return imgBaseUrl;
    }

    /**
     * @param imgBaseUrl - Setter function for set image base url of business
     */
    public void setImgBaseUrl(final String imgBaseUrl) {
        this.imgBaseUrl = imgBaseUrl;
    }

    /**
     * @return Business phone number
     */
    public String getFyndrBusinessPhoneNo() {
        return fyndrBusinessPhoneNo;
    }

    /**
     * @param fyndrBizPhone - Setter function for set business phone number
     */
    public void setFyndrBusinessPhoneNo(final String fyndrBizPhone) {
        this.fyndrBusinessPhoneNo = fyndrBizPhone;
    }

    /**
     * @return Business website
     */
    public String getFyndrBusinessWebsite() {
        return fyndrBusinessWebsite;
    }

    /**
     * @param bizWebsite - Setter function for set business's website
     */
    public void setFyndrBusinessWebsite(final String bizWebsite) {
        this.fyndrBusinessWebsite = bizWebsite;
    }

    /**
     * @return Address of business in fyndr
     */
    public String getFyndrAddress() {
        return fyndrAddress;
    }

    /**
     * @param fyndrAddress - Setter function for set address of business in fyndr
     */
    public void setFyndrAddress(final String fyndrAddress) {
        this.fyndrAddress = fyndrAddress;
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "AccountStatusMailInfoDTO [firstName=" + firstName + ", businessName=" + businessName
                + ", accountStatus=" + accountStatus + ", imgBaseUrl=" + imgBaseUrl + ", fyndrBusinessPhoneNo="
                + fyndrBusinessPhoneNo + ", fyndrBusinessWebsite=" + fyndrBusinessWebsite + ", fyndrAddress="
                + fyndrAddress + "]";
    }

    /**
     * The hash Code method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountStatus, businessName, firstName, fyndrAddress, fyndrBusinessPhoneNo,
                fyndrBusinessWebsite, imgBaseUrl);
    }

    /**
     * The equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccountStatusMailInfoDTO)) {
            return false;
        }
        AccountStatusMailInfoDTO other = (AccountStatusMailInfoDTO) obj;
        return Objects.equals(accountStatus, other.accountStatus) && Objects.equals(businessName, other.businessName)
                && Objects.equals(firstName, other.firstName) && Objects.equals(fyndrAddress, other.fyndrAddress)
                && Objects.equals(fyndrBusinessPhoneNo, other.fyndrBusinessPhoneNo)
                && Objects.equals(fyndrBusinessWebsite, other.fyndrBusinessWebsite)
                && Objects.equals(imgBaseUrl, other.imgBaseUrl);
    }

}
