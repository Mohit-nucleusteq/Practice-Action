package us.fyndr.api.admin.dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 * . CampaignDetailsInDTO class has fields for filtering campaign details
 */
public class CampaignDetailsInDTO {

    /**
     * The Business Name.
     */
    private String businessName;

    /**
     * The Country.
     */
    private String country;

    /**
     * startDate.
     */
    private LocalDate startDate;

    /**
     * endDate.
     */
    private LocalDate endDate;

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
     * the hashcode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(businessName, country, endDate, startDate);
    }

    /**
     * the equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CampaignDetailsInDTO)) {
            return false;
        }
        CampaignDetailsInDTO other = (CampaignDetailsInDTO) obj;
        return Objects.equals(businessName, other.businessName) && Objects.equals(country, other.country)
                && Objects.equals(endDate, other.endDate) && Objects.equals(startDate, other.startDate);
    }

    /**
     * the toString method.
     */
    @Override
    public String toString() {
        return "CampaignDetailsInDTO [businessName=" + businessName + ", country=" + country + ", startDate="
                + startDate + ", endDate=" + endDate + "]";
    }

}
