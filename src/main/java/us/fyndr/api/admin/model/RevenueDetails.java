package us.fyndr.api.admin.model;

import java.util.Objects;

import us.fyndr.api.admin.util.Constants;

/**
 * The RevenueEntityDTO stores result of revenue details query.
 * */
public class RevenueDetails {

    /**
     * payment.
     */
    private Double payment;

    /**
     * businessName.
     */
    private String businessName;

    /**
     * channel.
     */
    private String channel;

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
     * @return the channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * @param channel the channel to set
     */
    public void setChannel(final String channel) {
        this.channel = channel;
    }

    /**
     * @return the payment
     */
    public Double getPayment() {
        return payment;
    }

    /**
     * @param payment the payment to set
     */
    public void setPayment(final Double payment) {
        if (Objects.nonNull(payment)) {
            this.payment = Math.round(payment * Constants.HUNDRED) / Constants.HUNDRED;
        }
    }

    /**
     * Parameterized constructor needed for storing data fetched from query.
     * @param payment stores sum of a channel
     * @param businessName stores name of business
     * @param channel stores different invoice channels
     */

    public RevenueDetails(final Double payment, final String businessName, final String channel) {
        if (Objects.nonNull(payment)) {
            this.payment = Math.round(payment * Constants.HUNDRED) / Constants.HUNDRED;
        }
        this.businessName = businessName;
        this.channel = channel;

    }

    /**
     * hashCode method.
     */
    @Override
    public int hashCode() {
        return Objects.hash(businessName, channel,
                payment);
    }

    /**
     * equals method.
    */
    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RevenueDetails)) {
            return false;
        }
        RevenueDetails other = (RevenueDetails) obj;
        return Objects.equals(businessName, other.businessName)
                && Objects.equals(channel, other.channel)
                && Objects.equals(payment, other.payment);
    }

    /**
     * toString method.
     */
    @Override
    public String toString() {
        return "RevenueDetails [payment=" + payment + ", businessName="
                + businessName + ", channel=" + channel + "]";
    }

}
