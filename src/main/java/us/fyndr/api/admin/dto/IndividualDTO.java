package us.fyndr.api.admin.dto;

import java.util.Objects;

import us.fyndr.api.admin.dbo.AccountStatus;

/**
 * IndividualDTO class contains the inforamtion of individual user which will be
 * used in asyncronouslyUpdateProfile.
 *
 */
public class IndividualDTO {

    /**
     * The Qr Id of individual user.
     */
    private Long codeMasterQrId;

    /**
     * The date of birth of individual user.
     */
    private String yob;

    /**
     * The gender of individual user.
     */
    private String gender;

    /**
     * The country of individual user.
     */
    private String country;

    /**
     * The city of individual user.
     */
    private String city;

    /**
     * The fyndrHandle of individual user.
     */
    private String fyndrHandle;

    /**
     * The accountStatus of individual user.
     */
    private AccountStatus accountStatus;

    /**
     * @return Qr Id of individual user
     */
    public Long getCodeMasterQrId() {
        return codeMasterQrId;
    }

    /**
     * @param qrId setter method to set Qr Id of individual user
     */
    public void setCodeMasterQrId(final Long qrId) {
        this.codeMasterQrId = qrId;
    }

    /**
     * @return date of birth of individual user
     */
    public String getYob() {
        return yob;
    }

    /**
     * @param yob setter method to set date of birth of individual user
     */
    public void setYob(final String yob) {
        this.yob = yob;
    }

    /**
     * @return gender of individual user
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender setter method to set gender of individual user
     */
    public void setGender(final String gender) {
        this.gender = gender;
    }

    /**
     * @return country of individual user
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country setter method to set country of individual user
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * @return city of individual user
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city setter method to set city of individual user
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * @return fyndrHandle of individual user
     */
    public String getFyndrHandle() {
        return fyndrHandle;
    }

    /**
     * @param fyndrHandle setter method to set fyndrHandle of individual user
     */
    public void setFyndrHandle(final String fyndrHandle) {
        this.fyndrHandle = fyndrHandle;
    }

    /**
     * @return accountStatus of individual user
     */
    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    /**
     * @param accountStatus setter method to set accountStatus of individual user
     */
    public void setAccountStatus(final AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "IndividualDTO [codeMasterQrId=" + codeMasterQrId + ", yob=" + yob + ", gender=" + gender + ", country="
                + country + ", city=" + city + ", fyndrHandle=" + fyndrHandle + ", accountStatus=" + accountStatus
                + "]";
    }

    /**
     * The hashcode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(accountStatus, city, codeMasterQrId, country, fyndrHandle, gender, yob);
    }

    /**
     * The equals method.
     */
    @Override
    public final boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof IndividualDTO)) {
            return false;
        }
        IndividualDTO other = (IndividualDTO) obj;
        return Objects.equals(accountStatus, other.accountStatus) && Objects.equals(city, other.city)
                && Objects.equals(codeMasterQrId, other.codeMasterQrId) && Objects.equals(country, other.country)
                && Objects.equals(fyndrHandle, other.fyndrHandle) && Objects.equals(gender, other.gender)
                && Objects.equals(yob, other.yob);
    }

}
