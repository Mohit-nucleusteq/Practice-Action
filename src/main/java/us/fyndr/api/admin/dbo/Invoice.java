package us.fyndr.api.admin.dbo;

import java.time.Instant;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;

import org.springframework.data.rest.core.annotation.Description;

import us.fyndr.api.admin.model.RevenueDetails;

@SqlResultSetMapping(name = "RevenueDetails", classes = {
        @ConstructorResult(targetClass = RevenueDetails.class,
                columns =  {@ColumnResult(name = "payment", type = Double.class),
                            @ColumnResult(name = "businessName", type = String.class),
                            @ColumnResult(name = "channel", type = String.class)})
        })
@Entity
@Table(name = "invoice")
public class Invoice {

    /**
     * The objId.
     */
    @Id
    @Column(name = "objid")
    private Long objId;

    /**
     * The tax Amount.
     */
    @Description(value = "Invoice tax amount")
    @Column(name = "tax_amount")
    private Double taxAmount;

    /**
     * The base Amount.
     */
    @Description(value = "Invoice base amount")
    @Column(name = "base_amount")
    private Double baseAmount;

    /**
     * The status of invoice -paid, pending, cancel.
     */
    @Description(value = "Invoice status")
    @Column(name = "status")
    private String status;

    /**
     * The different channels of invoice - offer, promotion, catalogue, interaction.
     */
    @Description(value = "Invoice channel")
    @Column(name = "channel")
    private String channel;

    /**
     * The tip amount.
     */
    @Description(value = "Invoice Tip Amount")
    @Column(name = "tip_amount")
    private Double tipAmount;

    /**
     * The discount amount.
     */
    @Description(value = "Invoice discount amount ")
    @Column(name = "discount_amount")
    private Double discountAmount;

    /**
     * The date when invoice created.
     */
    @Column(name = "invoice_dt")
    private Instant invoiceDate;

    /**
     * The business Id.
     */
    @Column(name = "bizid")
    private Long businessId;

    /**
     * The currency type.
     */
    @Column(name = "currency")
    private String currency;

    /**
     * The currency symbol.
     */
    @Column(name = "currency_symbol")
    private String currencySymbol;

    /**
     * @return the objId
     */
    public Long getObjId() {
        return objId;
    }

    /**
     * @param objId the setter method to set objId
     */
    public void setObjId(final Long objId) {
        this.objId = objId;
    }

    /**
     * @return the tax Amount
     */
    public Double getTaxAmount() {
        return taxAmount;
    }

    /**
     * @param taxAmount the setter method to set tax amount
     */
    public void setTaxAmount(final Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    /**
     * @return the base Amount
     */
    public Double getBaseAmount() {
        return baseAmount;
    }

    /**
     * @param baseAmount the setter method to set baseAmount
     */
    public void setBaseAmount(final Double baseAmount) {
        this.baseAmount = baseAmount;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the setter method to set status
     */
    public void setStatus(final String status) {
        this.status = status;
    }

    /**
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the setter method to set channel
     */
    public void setChannel(final String channel) {
        this.channel = channel;
    }

    /**
     * @return the tip Amount
     */
    public Double getTipAmount() {
        return tipAmount;
    }

    /**
     * @param tipAmount the setter method to set tipAmount
     */
    public void setTipAmount(final Double tipAmount) {
        this.tipAmount = tipAmount;
    }

    /**
     * @return the discountAmount
     */
    public Double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * @param discountAmount the setter method to set discountAmount
     */
    public void setDiscountAmount(final Double discountAmount) {
        this.discountAmount = discountAmount;
    }

    /**
     * @return the date when invoice created
     */
    public Instant getInvoiceDate() {
        return invoiceDate;
    }

    /**
     * @param invoiceDate the setter method to set invoiceDate
     */
    public void setInvoiceDate(final Instant invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    /**
     * @return the business Id
     */
    public Long getBusinessId() {
        return businessId;
    }

    /**
     * @param businessId the setter method to set business Id
     */
    public void setBusinessId(final Long businessId) {
        this.businessId = businessId;
    }

    /**
     * @return the type of currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(final String currency) {
        this.currency = currency;
    }

    /**
     * @return the currencySymbol
     */
    public String getCurrencySymbol() {
        return currencySymbol;
    }

    /**
     * @param currencySymbol the currencySymbol to set
     */
    public void setCurrencySymbol(final String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    /**
     * The hash code method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(baseAmount, businessId, channel, currency,
                currencySymbol, discountAmount, invoiceDate, objId, status,
                taxAmount, tipAmount);
    }

    /**
     * The equals method.
     */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Invoice)) {
            return false;
        }
        Invoice other = (Invoice) obj;
        return Objects.equals(baseAmount, other.baseAmount)
                && Objects.equals(businessId, other.businessId)
                && Objects.equals(channel, other.channel)
                && Objects.equals(currency, other.currency)
                && Objects.equals(currencySymbol, other.currencySymbol)
                && Objects.equals(discountAmount, other.discountAmount)
                && Objects.equals(invoiceDate, other.invoiceDate)
                && Objects.equals(objId, other.objId)
                && Objects.equals(status, other.status)
                && Objects.equals(taxAmount, other.taxAmount)
                && Objects.equals(tipAmount, other.tipAmount);
    }

    /**
     * The toString method.
     */
    @Override
    public String toString() {
        return "Invoice [objId=" + objId + ", taxAmount=" + taxAmount
                + ", baseAmount=" + baseAmount + ", status=" + status
                + ", channel=" + channel + ", tipAmount=" + tipAmount
                + ", discountAmount=" + discountAmount + ", invoiceDate="
                + invoiceDate + ", businessId=" + businessId + ", currency="
                + currency + ", currencySymbol=" + currencySymbol + "]";
    }

}
