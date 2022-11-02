package us.fyndr.api.admin.dbo;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.Instant;

import org.junit.jupiter.api.Test;

public class IndividualDBOTest {

    @Test
    public void testGetterAndSetter() {
        Individual individualDBO = new Individual();

        assertNull(individualDBO.getObjId());
        long objid = 123l;
        individualDBO.setObjId(objid);
        assertEquals(objid, individualDBO.getObjId());

        assertNull(individualDBO.getBusinessId());
        long businessId = 1001l;
        individualDBO.setBusinessId(businessId);

        assertEquals(businessId, individualDBO.getBusinessId());

        assertNull(individualDBO.getFirstName());
        String firstName = "firstName";
        individualDBO.setFirstName(firstName);
        assertEquals(firstName, individualDBO.getFirstName());

        assertNull(individualDBO.getLastName());
        String lastName = "lastName";
        individualDBO.setLastName(lastName);
        assertEquals(lastName, individualDBO.getLastName());

        assertNull(individualDBO.getPhone());
        String phone = "phone";
        individualDBO.setPhone(phone);
        assertEquals(phone, individualDBO.getPhone());

        assertNull(individualDBO.getCountryCode());
        String countryCode = "country code";
        individualDBO.setCountryCode(countryCode);
        assertEquals(countryCode, individualDBO.getCountryCode());

        assertNull(individualDBO.getEmail());
        String email = "email";
        individualDBO.setEmail(email);
        assertEquals(email, individualDBO.getEmail());

        assertNull(individualDBO.getCreatedDt());
        Instant createdDt = Instant.now();
        individualDBO.setCreatedDt(createdDt);
        assertEquals(createdDt, individualDBO.getCreatedDt());

        assertNull(individualDBO.getAddressLine1());
        String addressLine1 = "addressLine1";
        individualDBO.setAddressLine1(addressLine1);
        assertEquals(addressLine1, individualDBO.getAddressLine1());

        assertNull(individualDBO.getAddressLine2());
        String addressLine2 = "addressLine2";
        individualDBO.setAddressLine2(addressLine2);
        assertEquals(addressLine2, individualDBO.getAddressLine2());

        assertNull(individualDBO.getCity());
        String city = "city";
        individualDBO.setCity(city);
        assertEquals(city, individualDBO.getCity());

        assertNull(individualDBO.getCountry());
        String country = "country";
        individualDBO.setCountry(country);
        assertEquals(country, individualDBO.getCountry());

        assertNull(individualDBO.getPostalCode());
        String postalCode = "postalCode";
        individualDBO.setPostalCode(postalCode);
        assertEquals(postalCode, individualDBO.getPostalCode());

        assertNull(individualDBO.getState());
        String state = "state";
        individualDBO.setState(state);
        assertEquals(state, individualDBO.getState());

        assertNull(individualDBO.getFyndrHandle());
        String fyndrHandle = "fyndrHandle";
        individualDBO.setFyndrHandle(fyndrHandle);
        assertEquals(fyndrHandle, individualDBO.getFyndrHandle());

        assertNull(individualDBO.getYob());
        String yob = "yob";
        individualDBO.setYob(yob);
        assertEquals(yob, individualDBO.getYob());

        assertNull(individualDBO.getGender());
        String gender = "gender";
        individualDBO.setGender(gender);
        assertEquals(gender, individualDBO.getGender());

        assertNull(individualDBO.getQrid());
        long qrId = 23l;
        individualDBO.setQrid(qrId);
        assertEquals(qrId, individualDBO.getQrid());

        assertFalse(individualDBO.isBusiness());
        boolean isBusiness = true;
        individualDBO.setIsBusiness(isBusiness);
        assertEquals(isBusiness, individualDBO.isBusiness());

        AccountStatus accountStatus = AccountStatus.ACTIVE;
        individualDBO.setAccountStatus(accountStatus);
        assertEquals(accountStatus, individualDBO.getAccountStatus());

    }

    @Test
    public void testToString() {
        Individual individualDBO = new Individual();
        long objid = 123;
        individualDBO.setObjId(objid);
        long qrid = 212;
        individualDBO.setQrid(qrid);
        long bizid = 1001;
        individualDBO.setBusinessId(bizid);

        String firstName = "firstName";
        individualDBO.setFirstName(firstName);
        String lastName = "lastName";
        individualDBO.setLastName(lastName);
        String phone = "phone";
        individualDBO.setPhone(phone);
        String countryCode = "country code";
        individualDBO.setCountryCode(countryCode);
        String email = "email";
        individualDBO.setEmail(email);
        Instant createdDt = Instant.now();
        individualDBO.setCreatedDt(createdDt);
        String addressLine1 = "addressLine1";
        individualDBO.setAddressLine1(addressLine1);
        String addressLine2 = "addressLine2";
        individualDBO.setAddressLine2(addressLine2);
        String city = "city";
        individualDBO.setCity(city);
        String country = "country";
        individualDBO.setCountry(country);
        String postalCode = "postalCode";
        individualDBO.setPostalCode(postalCode);
        String fyndrHandle = "fyndr handle";
        individualDBO.setFyndrHandle(fyndrHandle);
        String yob = "yob";
        individualDBO.setYob(yob);
        String gender = "gender";
        individualDBO.setGender(gender);
        String state = "state";
        individualDBO.setState(state);
        boolean isBusiness = true;
        individualDBO.setIsBusiness(isBusiness);
        AccountStatus accountStatus = AccountStatus.ACTIVE;
        individualDBO.setAccountStatus(accountStatus);
        assertEquals(
                "Individual [objId=123, qrid=212, businessId=1001, firstName=firstName, yob=yob, gender=gender, lastName=lastName, fyndrHandle=fyndr handle, phone=phone, countryCode=country code, email=email, createdDt="
                        + createdDt
                        + ", addressLine1=addressLine1, addressLine2=addressLine2, city=city, state=state, country=country, postalCode=postalCode, isBusiness=true, accountStatus=ACTIVE]",
                individualDBO.toString());

    }

    @Test
    public void testEqualsAndHashcode() {
        long objid = 123;

        long qrId = 344;

        long businessId = 1001;

        String firstName = "firstName";

        String lastName = "lastName";

        String phone = "phone";

        String countryCode = "country code";

        String email = "email";

        Instant createdDt = Instant.now();

        String addressLine1 = "addressLine1";

        String addressLine2 = "addressLine2";

        String city = "city";

        String country = "country";

        String postalCode = "postalCode";

        String fyndrHandle = "fyndrHandle";

        String yob = "yob";

        String gender = "gender";

        boolean isBusiness = true;

        String state = "state";

        AccountStatus accountStatus = AccountStatus.ACTIVE;

        Individual individualDBO1 = buildIndividualDbo(objid, qrId, businessId, firstName, lastName, phone, countryCode,
                email, createdDt, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender,
                state, isBusiness, accountStatus);

        assertEquals(individualDBO1, individualDBO1);
        assertEquals(individualDBO1.hashCode(), individualDBO1.hashCode());
        assertNotEquals(individualDBO1, new Object());

        Individual individualDBO2 = buildIndividualDbo(objid, qrId, businessId, firstName, lastName, phone, countryCode,
                email, createdDt, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender,
                state, isBusiness, accountStatus);
        assertEquals(individualDBO1, individualDBO2);
        assertEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, 31l, businessId, firstName, lastName, phone, countryCode, email,
                createdDt, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state, isBusiness,
                accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName + " ", lastName, phone, countryCode, email,
                createdDt, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName + " ", phone, countryCode, email,
                createdDt, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone + " ", countryCode, email,
                createdDt, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode + " ", email,
                createdDt, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email + " ",
                createdDt, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, businessId, firstName, lastName, phone, countryCode, email,
                Instant.MIN, addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1 + " ", addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email + " ",
                createdDt, addressLine1, addressLine2 + " ", city, country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city + " ", country, postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country + " ", postalCode, fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country, postalCode + " ", fyndrHandle, yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country, postalCode, fyndrHandle + " ", yob, gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob + " ", gender, state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender + " ", state,
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state + " ",
                isBusiness, accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state, false,
                accountStatus);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO2 = buildIndividualDbo(objid, qrId, 45l, firstName, lastName, phone, countryCode, email, createdDt,
                addressLine1, addressLine2, city, country, postalCode, fyndrHandle, yob, gender, state, false,
                AccountStatus.DELETED);
        assertNotEquals(individualDBO1, individualDBO2);
        assertNotEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

        individualDBO1 = new Individual();
        individualDBO2 = new Individual();
        assertEquals(individualDBO1, individualDBO2);
        assertEquals(individualDBO1.hashCode(), individualDBO2.hashCode());

    }

    private Individual buildIndividualDbo(Long objid, Long qrId, Long businessId, String firstName, String lastName,
            String phone, String countryCode, String email, Instant createdDt, String addressLine1, String addressLine2,
            String city, String state, String country, String postalCode, String fyndrHandle, String yob, String gender,
            boolean isBusiness, AccountStatus accountStatus) {
        Individual individualDBO = new Individual();
        individualDBO.setObjId(objid);

        individualDBO.setQrid(qrId);

        individualDBO.setBusinessId(businessId);

        individualDBO.setFirstName(firstName);

        individualDBO.setLastName(lastName);

        individualDBO.setPhone(phone);

        individualDBO.setCountryCode(countryCode);

        individualDBO.setEmail(email);

        individualDBO.setCreatedDt(createdDt);

        individualDBO.setAddressLine1(addressLine1);

        individualDBO.setAddressLine2(addressLine2);

        individualDBO.setCity(city);

        individualDBO.setCountry(country);

        individualDBO.setPostalCode(postalCode);

        individualDBO.setFyndrHandle(fyndrHandle);

        individualDBO.setYob(yob);

        individualDBO.setGender(gender);

        individualDBO.setState(state);

        individualDBO.setIsBusiness(isBusiness);

        individualDBO.setAccountStatus(accountStatus);

        return individualDBO;
    }

}