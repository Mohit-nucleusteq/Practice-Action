package us.fyndr.api.admin.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import us.fyndr.api.admin.dbo.Country;

/**
 * @author prach
 *
 */
@Repository
public interface CountryRepository extends CrudRepository<Country, Long> {

    /**
     * findByIsoCode method to fetch country object based on country name.
     * @param isoCode - country name
     * @return country - object of Country class
     */
    Country findByIsoCode(@Param("isoCode") String isoCode);

    /**
     * getCurrencyByIndividualId - fetches currency by Individual ID.
     * @param individualId is a object id of indv_master table.
     * @return currency.
     */
    @Query("SELECT c.currency FROM Country c JOIN Individual im on im.country = c.isoCode WHERE im.objId = ?1 ")
    String getCurrencyByIndividualId(Long individualId);

    /**
     * getCurrencySymbolByIndividualId- fetches currency symbol by Individual ID.
     * @param individualId is a object id of indv_master table.
     * @return currency symbol.
     */
    @Query("SELECT c.currencySymbol FROM Country c JOIN Individual im on im.country = c.isoCode WHERE im.objId = ?1 ")
    String getCurrencySymbolByIndividualId(Long individualId);
}
