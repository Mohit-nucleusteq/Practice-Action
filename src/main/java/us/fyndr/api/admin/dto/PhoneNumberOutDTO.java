package us.fyndr.api.admin.dto;

import java.util.Objects;

/**
 * . PhoneNumberOutDTO Class present the phone number details of user as an
 * output data
 */
public final class PhoneNumberOutDTO {

    /**
     * . The country code of a user
     */
    private String countryCode;

    /**
     * . The phone number of user
     */
    private String phoneNumber;

    /**
     * @return country code of user
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode sets the country code of user
     */
    public void setCountryCode(final String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the phonenumber of user
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber sets the phonenumber of a user
     */
    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "PhoneNumberOutDTO [countryCode=" + countryCode + ", phoneNumber=" + phoneNumber + "]";
    }

    /**
     * The hashcode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(countryCode, phoneNumber);
    }

    /**
     * The equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PhoneNumberOutDTO)) {
            return false;
        }
        PhoneNumberOutDTO other = (PhoneNumberOutDTO) obj;
        return Objects.equals(countryCode, other.countryCode) && Objects.equals(phoneNumber, other.phoneNumber);
    }
}
