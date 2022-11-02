package us.fyndr.api.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.dbo.Invoice;

/**
 * InvoiceRepository contains methods to access the data from invoice table.
 *
 */
@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>, JpaSpecificationExecutor<Invoice> {

    /**
     * @param country
     * @return revenue by status
     */
    @Query("SELECT SUM(i.baseAmount) + SUM(i.taxAmount) + SUM(i.tipAmount) - SUM(i.discountAmount) from Invoice "
            + "i join Country c on i.currencySymbol = c.currencySymbol and i.currency = c.currency where"
            + " i.status = 'paid' and c.isoCode = ?1")
    Double totalRevenue(String country);

    /**
     * @param channel
     * @param country
     * @return revenue by channel
     */
    @Query("SELECT SUM(i.baseAmount) + SUM(i.taxAmount) + SUM(i.tipAmount) - SUM(i.discountAmount) from Invoice "
            + "i join Country c on i.currencySymbol = c.currencySymbol and i.currency = c.currency where i.channel = ?1 "
            + "and i.status = 'paid' and c.isoCode = ?2")
    Double totalRevenueByChannel(String channel, String country);

    /**
     * @param businessName
     * @return currencySymbol
     */
    @Query("SELECT DISTINCT i.currencySymbol FROM Invoice i join Business bm on i.businessId = bm.objId "
            + "WHERE bm.businessName= ?1 and i.status= 'paid'")
    String fetchCurrencySymbolByBusinessName(String businessName);

    /**
     * @param businessName
     * @return currency
     */
    @Query("SELECT DISTINCT i.currency FROM Invoice i join Business bm on i.businessId = bm.objId "
            + "WHERE bm.businessName = ?1 and i.status= 'paid'")
    String fetchCurrencyByBusinessName(String businessName);

}
