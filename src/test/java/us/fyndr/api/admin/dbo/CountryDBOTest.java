package us.fyndr.api.admin.dbo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

public class CountryDBOTest {

    @Test
    public void testGetterAndSetter() {

        Country country = new Country();

        assertNull(country.getObjId());
        Long objId = 4L;
        country.setObjId(4L);
        assertEquals(objId, country.getObjId());

        assertNull(country.getIsoCode());
        String isoCode = "isoCode";
        country.setIsoCode(isoCode);
        assertEquals(isoCode, country.getIsoCode());

        assertNull(country.getCurrency());
        String currency = "currency";
        country.setCurrency(currency);
        assertEquals(currency, country.getCurrency());

        assertNull(country.getCurrencySymbol());
        String currencySymbol = "currencySymbol";
        country.setCurrencySymbol(currencySymbol);
        assertEquals(currencySymbol, country.getCurrencySymbol());
    }

    @Test
    public void testToString() {
        Country country = new Country();

        Long objId = 4L;
        country.setObjId(objId);

        String isoCode = "isoCode";
        country.setIsoCode(isoCode);

        String currency = "currency";
        country.setCurrency(currency);

        String currencySymbol = "currencySymbol";
        country.setCurrencySymbol(currencySymbol);

        assertEquals("Country [objId=4, isoCode=isoCode, currencySymbol=currencySymbol, currency=currency]",
                country.toString());
    }

    @Test
    public void testEqualsAndHashcode() {

        Long objId = 5L;
        String isoCode = "isoCode";
        String currency = "currency";
        String currencySymbol = "currencySymbol";

        Country country1 = buildCountryDBO(objId, isoCode, currency, currencySymbol);

        assertEquals(country1, country1);
        assertEquals(country1.hashCode(), country1.hashCode());

        assertNotEquals(country1, new Object());

        Country country2 = buildCountryDBO(objId, isoCode, currency, currencySymbol);
        assertEquals(country1, country2);
        assertEquals(country1.hashCode(), country2.hashCode());

        country2 = buildCountryDBO(6L, isoCode, currency, currencySymbol);
        assertNotEquals(country1, country2);
        assertNotEquals(country1.hashCode(), country2.hashCode());

        country2 = buildCountryDBO(objId, "isoCodee", currency, currencySymbol);
        assertNotEquals(country1, country2);
        assertNotEquals(country1.hashCode(), country2.hashCode());

        country2 = buildCountryDBO(objId, isoCode, "currencyy", currencySymbol);
        assertNotEquals(country1, country2);
        assertNotEquals(country1.hashCode(), country2.hashCode());

        country2 = buildCountryDBO(objId, isoCode, currency, "currencySymboll");
        assertNotEquals(country1, country2);
        assertNotEquals(country1.hashCode(), country2.hashCode());

        country1 = new Country();
        country2 = new Country();
        assertEquals(country1, country2);
        assertEquals(country1.hashCode(), country2.hashCode());

    }

    private Country buildCountryDBO(Long objId, String isoCode, String currency, String currencySymbol) {
        Country country = new Country();

        country.setObjId(objId);
        country.setIsoCode(isoCode);
        country.setCurrency(currency);
        country.setCurrencySymbol(currencySymbol);

        return country;
    }
}
