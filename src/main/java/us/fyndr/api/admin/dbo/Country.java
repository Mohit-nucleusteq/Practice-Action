package us.fyndr.api.admin.dbo;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "country")
public class Country {

    /**
     * The objId.
     */
    @Id
    @Column(name = "objid")
    private Long objId;

    /**
     * The isoCode.
     */
    @Column(name = "iso_code")
    private String isoCode;

    /**
     * The currency symbol.
     */
    @Column(name = "currency_symbol")
    private String currencySymbol;

    /**
     * The currency.
     */
    @Column(name = "currency")
    private String currency;

    /**
     * @return the objId
     */
    public Long getObjId() {
        return objId;
    }

    /**
     * @param objId the setter method to set objid
     */
    public void setObjId(final Long objId) {
        this.objId = objId;
    }

    /**
     * @return the isoCode
     */
    public String getIsoCode() {
        return isoCode;
    }

    /**
     * @param isoCode the the setter method to set isoCode
     */
    public void setIsoCode(final String isoCode) {
        this.isoCode = isoCode;
    }

    /**
     * @return the currencySymbol
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * @param currencySymbol the setter method to set currencySymbol
     */
    public void setCurrencySymbol(final String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the setter method to set currency
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * The hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(currency, currencySymbol, isoCode, objId);
    }

    /**
     * The equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Country)) {
            return false;
        }
        Country other = (Country) obj;
        return Objects.equals(currency, other.currency) && Objects.equals(currencySymbol, other.currencySymbol)
                && Objects.equals(isoCode, other.isoCode) && Objects.equals(objId, other.objId);
    }

    /**
     * toString method.
     */
    @Override
    public String toString() {
        return "Country [objId=" + objId + ", isoCode=" + isoCode + ", currencySymbol=" + currencySymbol + ", currency="
                + currency + "]";
    }

}
