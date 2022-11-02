package us.fyndr.api.admin.dto;

import java.util.Objects;

/**
 * . AddressOutDTO Class presents the data of an individual's address with
 * postal code as an output data
 */
public final class AddressOutDTO {

    /**
     * . The address line 1 of a user
     */
    private String addressLine1;

    /**
     * . The address line 2 of a user
     */
    private String addressLine2;

    /**
     * . The state of a user
     */
    private String state;

    /**
     * . The country of a user
     */
    private String country;

    /**
     * . The postal code of a user
     */
    private String postalCode;

    /**
     * . The city of a user
     */
    private String city;

    /**
     * @return address line1 of a user
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * @param addressLine1 sets address line1 of a user
     */
    public void setAddressLine1(final String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * @return the address line2 of a user
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * @param addressLine2 sets address line2 of a user
     */
    public void setAddressLine2(final String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * @return state of a user
     */
    public String getState() {
        return state;
    }

    /**
     * @param state sets the state of a user
     */
    public void setState(final String state) {
        this.state = state;
    }

    /**
     * @return the country of a user
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country sets country of a user
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * @return the postalcode of a user
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode sets the postalcode user
     */
    public void setPostalCode(final String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return city of a user
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city sets the city of a user
     */
    public void setCity(final String city) {
        this.city = city;
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "AddressOutDTO [addressLine1=" + addressLine1 + ", addressLine2=" + addressLine2 + ", state=" + state
                + ", country=" + country + ", postalCode=" + postalCode + ", city=" + city + "]";
    }

    /**
     * The hashcode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(addressLine1, addressLine2, city, country, postalCode, state);
    }

    /**
     * The equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AddressOutDTO)) {
            return false;
        }
        AddressOutDTO other = (AddressOutDTO) obj;
        return Objects.equals(addressLine1, other.addressLine1) && Objects.equals(addressLine2, other.addressLine2)
                && Objects.equals(city, other.city) && Objects.equals(country, other.country)
                && Objects.equals(postalCode, other.postalCode) && Objects.equals(state, other.state);
    }
}
