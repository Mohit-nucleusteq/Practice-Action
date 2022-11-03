package us.fyndr.api.admin.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class InvoiceDBOTest {

    @Test
    public void testGetterAndSetter() {
        Invoice invoiceDBO = new Invoice();

        assertNull(invoiceDBO.getObjId());
        long objId = 123L;
        invoiceDBO.setObjId(objId);
        assertEquals(objId, invoiceDBO.getObjId());

        assertNull(invoiceDBO.getTaxAmount());
        Double taxAmount = 56.7;
        invoiceDBO.setTaxAmount(taxAmount);
        assertEquals(taxAmount, invoiceDBO.getTaxAmount());

        assertNull(invoiceDBO.getBaseAmount());
        Double baseAmount = 57.9;
        invoiceDBO.setBaseAmount(baseAmount);
        assertEquals(baseAmount, invoiceDBO.getBaseAmount());

        assertNull(invoiceDBO.getStatus());
        String status = "paid";
        invoiceDBO.setStatus(status);
        assertEquals(status, invoiceDBO.getStatus());

        assertNull(invoiceDBO.getChannel());
        String channel = "offer";
        invoiceDBO.setChannel(channel);
        assertEquals(channel, invoiceDBO.getChannel());

        assertNull(invoiceDBO.getTipAmount());
        Double tipAmount = 54.9;
        invoiceDBO.setTipAmount(tipAmount);
        assertEquals(tipAmount, invoiceDBO.getTipAmount());

        assertNull(invoiceDBO.getDiscountAmount());
        Double discountAmount = 5.9;
        invoiceDBO.setDiscountAmount(discountAmount);
        assertEquals(discountAmount, invoiceDBO.getDiscountAmount());

        assertNull(invoiceDBO.getBusinessId());
        Long businessId = 4L;
        invoiceDBO.setBusinessId(businessId);
        assertEquals(businessId, invoiceDBO.getBusinessId());

        assertNull(invoiceDBO.getInvoiceDate());
        Instant invoiceDate = Instant.now();
        invoiceDBO.setInvoiceDate(invoiceDate);
        assertEquals(invoiceDate, invoiceDBO.getInvoiceDate());

        assertNull(invoiceDBO.getCurrency());
        String currency = "currency";
        invoiceDBO.setCurrency(currency);
        assertEquals(currency, invoiceDBO.getCurrency());
        
        assertNull(invoiceDBO.getCurrencySymbol());
        String currencySymbol = "currencySymbol";
        invoiceDBO.setCurrencySymbol(currencySymbol);
        assertEquals(currencySymbol, invoiceDBO.getCurrencySymbol());

    }

    @Test
    public void testToString() {
        Invoice invoiceDBO = new Invoice();

        long objId = 123L;
        invoiceDBO.setObjId(objId);

        Double taxAmount = 56.7;
        invoiceDBO.setTaxAmount(taxAmount);

        Double baseAmount = 57.9;
        invoiceDBO.setBaseAmount(baseAmount);

        String status = "paid";
        invoiceDBO.setStatus(status);

        String channel = "offer";
        invoiceDBO.setChannel(channel);

        Double tipAmount = 54.9;
        invoiceDBO.setTipAmount(tipAmount);

        Double discountAmount = 4.33;
        invoiceDBO.setDiscountAmount(discountAmount);

        Long businessId = 4L;
        invoiceDBO.setBusinessId(businessId);

        Instant invoiceDate = Instant.now();
        invoiceDBO.setInvoiceDate(invoiceDate);

        String currency = "currency";
        invoiceDBO.setCurrency(currency);
        
        String currencySymbol = "currencySymbol";
        invoiceDBO.setCurrencySymbol(currencySymbol);

        assertEquals("Invoice [objId=" + objId + ", taxAmount=56.7, baseAmount=57.9, status=paid, channel=offer, tipAmount=54.9, "
                + "discountAmount=4.33, invoiceDate=" + invoiceDate + ", businessId=4, currency=currency, currencySymbol=currencySymbol]", invoiceDBO.toString());
    }

    @Test
    public void testEqualsAndHashcode() {

        long objId = 123L;
        Double taxAmount = 56.7;
        Double baseAmount = 57.9;
        String status = "paid";
        String channel = "offer";
        Double tipAmount = 54.9;
        Double discountAmount = 4.33;
        Long businessId = 4L;
        Instant invoiceDate = Instant.now();
        String currency = "currency";        
        String currencySymbol = "currencySymbol";

        Invoice invoiceDBO1 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, channel, tipAmount, discountAmount,
                businessId, invoiceDate, currency, currencySymbol);

        assertEquals(invoiceDBO1, invoiceDBO1);
        assertEquals(invoiceDBO1.hashCode(), invoiceDBO1.hashCode());
        assertNotEquals(invoiceDBO1, new Object());

        Invoice invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, channel, tipAmount, discountAmount,
                businessId, invoiceDate, currency, currencySymbol);
        assertEquals(invoiceDBO1, invoiceDBO2);
        assertEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(3435, taxAmount, baseAmount, status, channel, tipAmount, discountAmount,
                businessId, invoiceDate, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, 56.77, baseAmount, status, channel, tipAmount, discountAmount, businessId,
                invoiceDate, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, 56.78, status, channel, tipAmount, discountAmount, businessId,
                invoiceDate, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, "pending", channel, tipAmount, discountAmount,
                businessId, invoiceDate, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, "offers", tipAmount, discountAmount,
                businessId, invoiceDate, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, channel, 67.8, discountAmount, businessId,
                invoiceDate, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, channel, tipAmount, 4.23, businessId,
                invoiceDate, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, channel, tipAmount, discountAmount, 5L,
                invoiceDate, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, channel, tipAmount, discountAmount,
                businessId, Instant.MIN, currency, currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, channel, tipAmount, discountAmount,
                businessId, invoiceDate, currency+" ", currencySymbol);
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO2 = buildInvoiceDbo(objId, taxAmount, baseAmount, status, channel, tipAmount, discountAmount,
                businessId, invoiceDate, currency, currencySymbol+" ");
        assertNotEquals(invoiceDBO1, invoiceDBO2);
        assertNotEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

        invoiceDBO1 = new Invoice();
        invoiceDBO2 = new Invoice();
        assertEquals(invoiceDBO1, invoiceDBO2);
        assertEquals(invoiceDBO1.hashCode(), invoiceDBO2.hashCode());

    }

    private Invoice buildInvoiceDbo(long objId, Double taxAmount, Double baseAmount, String status, String channel,
            Double tipAmount, Double discountAmount, Long businessId, Instant invoiceDate, String currency, String currencySymbol) {

        Invoice invoiceDBO = new Invoice();

        invoiceDBO.setObjId(objId);
        invoiceDBO.setTaxAmount(taxAmount);
        invoiceDBO.setBaseAmount(baseAmount);
        invoiceDBO.setStatus(status);
        invoiceDBO.setChannel(channel);
        invoiceDBO.setTipAmount(tipAmount);
        invoiceDBO.setDiscountAmount(discountAmount);
        invoiceDBO.setInvoiceDate(invoiceDate);
        invoiceDBO.setBusinessId(businessId);
        invoiceDBO.setCurrency(currency);
        invoiceDBO.setCurrencySymbol(currencySymbol);

        return invoiceDBO;
    }
}