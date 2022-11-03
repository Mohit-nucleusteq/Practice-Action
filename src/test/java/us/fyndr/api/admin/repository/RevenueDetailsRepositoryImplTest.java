package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import us.fyndr.api.admin.dbo.Invoice;
import us.fyndr.api.admin.model.RevenueDetails;

@DataJpaTest
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql", "classpath:/dml/repository/revenue_details_insertion.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:/dml/repository/revenue_details_deletion.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@ComponentScan(basePackages = {"us.fyndr.api.admin.repository"})
@Import(RevenueDetailsRepositoryImpl.class)
class RevenueDetailsRepositoryImplTest {

    @Autowired
    private InvoiceRepository invoiceRepository;
    
    @Autowired
    private RevenueDetailsRepositoryImpl revenueDetailsRepositoryImpl;

    @BeforeEach
    public void setup() {

        Instant invoiceDate = Instant.now();
        
        Invoice invoice = buildInvoice(1L, 10.01, 20.02, 30.03, 5.04, "paid", "offers", 2L, invoiceDate, "USD", "$");
        invoiceRepository.save(invoice);

        invoice = buildInvoice(2L, 10.08, 40.35, 50.25, 3.25, "pending", "promo", 2L, invoiceDate, "USD", "$");
        invoiceRepository.save(invoice);
        
        invoice = buildInvoice(3L,  56.08, 20.35, 30.25, 15.25, "paid", "offers", 2L, invoiceDate, "USD", "$");
        invoiceRepository.save(invoice);

        invoice = buildInvoice(4L, 18.08, 40.35, 50.25, 11.25, "paid", "interaction", 5L, invoiceDate, "USD", "$");
        invoiceRepository.save(invoice);

        invoice = buildInvoice(5L, 11.08, 40.35, 50.25, 3.25, "paid", "offers", 5L, invoiceDate, "USD", "$");
        invoiceRepository.save(invoice);

        invoice = buildInvoice(6L, 17.08, 40.35, 50.25, 6.25, "paid", "offers", 5L, invoiceDate, "USD", "$");
        invoiceRepository.save(invoice);

    }

    //@Test
    public void testRevenueDetailsWithoutPagination() {
        int maxResults = 3;

        List<RevenueDetails> expectedRevenueDetails = new ArrayList<>();
        
        RevenueDetails revenueEntityDTO = new RevenueDetails(146.45, "bizName1", "offers");

        expectedRevenueDetails.add(revenueEntityDTO);
        
        revenueEntityDTO = new RevenueDetails(97.43, "bizName2", "interaction");

        expectedRevenueDetails.add(revenueEntityDTO);

        revenueEntityDTO = new RevenueDetails(199.86, "bizName2", "offers");

        expectedRevenueDetails.add(revenueEntityDTO);

        String whereConditions = "";

        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1", "bizName2"});

        List<RevenueDetails> actualRevenueDetails = revenueDetailsRepositoryImpl.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList);

        assertEquals(expectedRevenueDetails, actualRevenueDetails);
        assertEquals(maxResults, actualRevenueDetails.size());
    }

    //@Test
    public void testRevenueDetailsPagination() {
        int maxResults = 1;

        String whereConditions = "";
        
        List<RevenueDetails> expectedRevenueDetails = new ArrayList<>();
        RevenueDetails revenueDetails = new RevenueDetails(146.45, "bizName1", "offers");

        expectedRevenueDetails.add(revenueDetails);
        
        List<String> businessNamesList = Arrays.asList(new String[] {"bizName1"});

        List<RevenueDetails> actualRevenueDetails = revenueDetailsRepositoryImpl.fetchRevenueDetailsByFilterCriteria(whereConditions, businessNamesList);

        assertEquals(expectedRevenueDetails, actualRevenueDetails);
        assertEquals(maxResults, actualRevenueDetails.size());

    }

    //@Test
    public void testCountRevenueDetails() {

        String whereConditions = "";
        int actualCount = revenueDetailsRepositoryImpl.countTotalRevenueDetailsFetchByFilterCriteria(whereConditions);
        assertEquals(2, actualCount);
    }

    //@Test
    public void testFetchBusinessNamesByFilterCriteria() {
        int pageNumber = 0;
        int maxResults = 1;

        String whereConditions = "";
        
        List<String> expectedBusinessNameList = new ArrayList<String>();
        expectedBusinessNameList.add("bizName1");

        List<String> actualBusinessNameList = revenueDetailsRepositoryImpl.fetchBusinessNamesByFilterCriteria(whereConditions, pageNumber, maxResults);
        
        assertEquals(expectedBusinessNameList, actualBusinessNameList);
        assertEquals(maxResults, actualBusinessNameList.size());
    }

    private Invoice buildInvoice(Long objId, Double taxAmount, Double baseAmount, Double tipAmount, Double discountAmount, 
            String status, String channel, Long businessId, Instant invoiceDate, String currency, String currencySymbol) {

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
