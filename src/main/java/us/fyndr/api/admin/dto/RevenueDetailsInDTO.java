package us.fyndr.api.admin.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * The RevenueDetailsInDTO stores criteria for filtering results for
 * fetching revenue details.
 */
public class RevenueDetailsInDTO {

    /**
     * country.
     */
    private String country;

    /**
     * businessName.
     */
    private String businessName;

    /**
     * startDate.
     */
    private LocalDate startDate;

    /**
     * endDate.
     */
    private LocalDate endDate;

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(final String country) {
        this.country = country;
    }

    /**
     * @return the businessName
     */
    public String getBusinessName() {
        return businessName;
    }

    /**
     * @param businessName the businessName to set
     */
    public void setBusinessName(final String businessName) {
        this.businessName = businessName;
    }

    /**
     * @return the startDate
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(final LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(final LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * hashCode().
     */
    @Override
    public int hashCode() {
        return Objects.hash(businessName, country, startDate, endDate);
    }

    /**
     * equals().
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RevenueDetailsInDTO)) {
            return false;
        }
        RevenueDetailsInDTO other = (RevenueDetailsInDTO) obj;
        return Objects.equals(businessName, other.businessName)
                && Objects.equals(country, other.country)
                && Objects.equals(startDate, other.startDate)
                && Objects.equals(endDate, other.endDate);
    }

    /**
     * toString().
     */
    @Override
    public String toString() {
        return "RevenueDetailsInDTO [country=" + country + ", businessName="
                + businessName + ", startDate=" + startDate + ", endDate="
                + endDate + "]";
    }
}
