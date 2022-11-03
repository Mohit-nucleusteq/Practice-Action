package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import us.fyndr.api.admin.dbo.Country;
import us.fyndr.api.admin.dbo.Business;
import us.fyndr.api.admin.dbo.Invoice;

@DataJpaTest
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class InvoiceRepositoryTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private CountryRepository countryRepository;
    
    @Autowired
    private BusinessRepository businessRepository;

    @BeforeEach
    public void setup() {


        Country country = new Country();
        country.setObjId(5L);
        country.setCurrency("USD");
        country.setCurrencySymbol("#");
        country.setIsoCode("US");

        countryRepository.save(country);
        
        Invoice invoice = buildInvoice(1L, 10.20, 6.10, 7.0, 0.0, "paid", "offers", 1L, "USD", "#");
        invoiceRepository.save(invoice);

        invoice = buildInvoice(2L, 20.100, 6.10, 7.02, 1.2, "paid", "catalog", 2L, "USD", "#");
        invoiceRepository.save(invoice);

        invoice = buildInvoice(3L, 24.50, 4.002, 4.011, 2.3, "paid", "promo", 1L, "USD", "#");
        invoiceRepository.save(invoice);

        invoice = buildInvoice(4L, 60.3200, 4.023, 2.101, 0.233, "paid", "interaction", 2L, "USD", "#");
        invoiceRepository.save(invoice);

        Business business = buildBusiness(1L, "bizName1", "food", "testwebsite1");
        businessRepository.save(business);

        business = buildBusiness(2L, "bizName2", "food", "testwebsite2");
        businessRepository.save(business);

    }

    //@Test
    public void testTotalPaymentProcess() {

        Double expectedTotalPaymentProcess = 151.744;
        String country = "US";

        Double actualTotalPaymentProcess = invoiceRepository.totalRevenue(country);

        assertEquals(expectedTotalPaymentProcess, actualTotalPaymentProcess);
    }

    //@Test
    public void testByChannelOffer() {

        Double expectedOfferPayment = 23.299999999999997;
        String country = "US";

        Double actualOfferPayment = invoiceRepository.totalRevenueByChannel("offers", country);

        assertEquals(expectedOfferPayment, actualOfferPayment);
    }

    //@Test
    public void testByChannelInteraction() {

        Double expectedInteractionPayment = 66.211;
        String country = "US";

        Double actualInteractionPayment = invoiceRepository.totalRevenueByChannel("interaction", country);

        assertEquals(expectedInteractionPayment, actualInteractionPayment);
    }

    //@Test
    public void testByChannelPromo() {

        Double expectedPromoPayment = 30.212999999999997;
        String country = "US";

        Double actualPromoPayment = invoiceRepository.totalRevenueByChannel("promo", country);

        assertEquals(expectedPromoPayment, actualPromoPayment);
    }

    //@Test
    public void testByChannelCatalog() {

        Double expectedCatalogPayment = 32.019999999999996;
        String country = "US";

        Double actualCatalogPayment = invoiceRepository.totalRevenueByChannel("catalog", country);

        assertEquals(expectedCatalogPayment, actualCatalogPayment);
    }

    //@Test
    public void fetchCurrencyByBusinessNameTest() {

        String expectedCurrency = "USD";
        String actualCurrency = invoiceRepository.fetchCurrencyByBusinessName("bizName1");
        assertEquals(expectedCurrency, actualCurrency);

    }

    //@Test
    public void fetchCurrencySymbolByBusinessNameTest() {

        String expectedCurrencySymbol = "#";
        String actualCurrencySymbol = invoiceRepository.fetchCurrencySymbolByBusinessName("bizName2");
        assertEquals(expectedCurrencySymbol, actualCurrencySymbol);

    }

    private Invoice buildInvoice(Long objId, Double baseAmount, Double taxAmount, Double tipAmount,
            Double discountAmount, String status, String channel, Long businessId, String currency, String currencySymbol) {

        Invoice invoice = new Invoice();
        invoice.setObjId(objId);
        invoice.setBaseAmount(baseAmount);
        invoice.setTaxAmount(taxAmount);
        invoice.setTipAmount(tipAmount);
        invoice.setDiscountAmount(discountAmount);
        invoice.setStatus(status);
        invoice.setChannel(channel);
        invoice.setBusinessId(businessId);
        invoice.setCurrency(currency);
        invoice.setCurrencySymbol(currencySymbol);

        return invoice;
    }

    private Business buildBusiness(Long objId, String businessName, String bizType,
            String website) {

        Business business = new Business();
        
        business.setObjId(objId);
        business.setBusinessName(businessName);
        business.setBizType(bizType);
        business.setWebsite(website);

        return business;

    }

}
