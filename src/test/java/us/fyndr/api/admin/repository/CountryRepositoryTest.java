package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import us.fyndr.api.admin.dbo.Country;

@DataJpaTest
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql", "classpath:/dml/repository/country_insertion.sql" })
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    Country country = new Country();

    @BeforeEach
    public void setup() {

        country.setObjId(5L);
        country.setCurrency("US");
        country.setCurrencySymbol("$");
        country.setIsoCode("US");

        countryRepository.save(country);
    }

    @Test
    public void testfindByIsoCodeMethod() {

        String isoCode = "US";

        Country actualCountry = countryRepository.findByIsoCode(isoCode);

        assertEquals(country, actualCountry);
    }

    @Test
    public void testgetCurrencyByIndividualId() {
        Long individualId = 103l;
        String currency = countryRepository.getCurrencyByIndividualId(individualId);
        assertEquals("US", currency);

    }

    @Test
    public void testgetCurrencySymbolByIndividualId() {
        Long individualId = 103l;
        String currencySymbol = countryRepository.getCurrencySymbolByIndividualId(individualId);
        assertEquals("$", currencySymbol);
    }

}
