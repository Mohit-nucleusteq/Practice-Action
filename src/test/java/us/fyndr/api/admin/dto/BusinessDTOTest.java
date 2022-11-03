package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.dbo.AccountStatus;

public class BusinessDTOTest {

    @Test
    public void testGetterAndSetter() {

        BusinessDTO businessDTO = new BusinessDTO();

        assertNull(businessDTO.getBizQRId());
        Long bizQRId = 12L;
        businessDTO.setBizQRId(bizQRId);
        assertEquals(bizQRId, businessDTO.getBizQRId());

        assertNull(businessDTO.getAddressLine1());
        String addressLine1 = "addressLine1";
        businessDTO.setAddressLine1(addressLine1);
        assertEquals(addressLine1, businessDTO.getAddressLine1());

        assertNull(businessDTO.getAddressLine2());
        String addressLine2 = "addressLine2";
        businessDTO.setAddressLine2(addressLine2);
        assertEquals(addressLine2, businessDTO.getAddressLine2());

        assertNull(businessDTO.getBusinessType());
        String businessType = "businessType";
        businessDTO.setBusinessType(businessType);
        assertEquals(businessType, businessDTO.getBusinessType());

        assertNull(businessDTO.getFyndrHandle());
        String fyndrHandle = "fyndrHandle";
        businessDTO.setFyndrHandle(fyndrHandle);
        assertEquals(fyndrHandle, businessDTO.getFyndrHandle());

        assertNull(businessDTO.getCity());
        String city = "city";
        businessDTO.setCity(city);
        assertEquals(city, businessDTO.getCity());

        assertNull(businessDTO.getCountry());
        String country = "country";
        businessDTO.setCountry(country);
        assertEquals(country, businessDTO.getCountry());

        assertNull(businessDTO.getState());
        String state = "state";
        businessDTO.setState(state);
        assertEquals(state, businessDTO.getState());

        assertNull(businessDTO.getPostalCode());
        String postalCode = "postalCode";
        businessDTO.setPostalCode(postalCode);
        assertEquals(postalCode, businessDTO.getPostalCode());

        assertNull(businessDTO.getAccountStatus());
        businessDTO.setAccountStatus(AccountStatus.ACTIVE);
        assertEquals(AccountStatus.ACTIVE, businessDTO.getAccountStatus());
    }

    @Test
    public void testToString() {
        BusinessDTO businessDTO = new BusinessDTO();

        long bizQRId = 12;
        businessDTO.setBizQRId(bizQRId);

        String addressLine1 = "addressLine1";
        businessDTO.setAddressLine1(addressLine1);

        String addressLine2 = "addressLine2";
        businessDTO.setAddressLine2(addressLine2);

        String businessType = "businessType";
        businessDTO.setBusinessType(businessType);

        String fyndrHandle = "fyndrHandle";
        businessDTO.setFyndrHandle(fyndrHandle);

        String city = "city";
        businessDTO.setCity(city);

        String country = "country";
        businessDTO.setCountry(country);

        String state = "state";
        businessDTO.setState(state);

        String postalCode = "postalCode";
        businessDTO.setPostalCode(postalCode);

        businessDTO.setAccountStatus(AccountStatus.ACTIVE);

        assertEquals(
                "BusinessDTO [bizQRId=12, addressLine1=addressLine1, addressLine2=addressLine2, businessType=businessType, "
                        + "fyndrHandle=fyndrHandle, city=city, country=country, state=state, postalCode=postalCode, accountStatus=ACTIVE]",
                businessDTO.toString());
    }

    @Test
    public void testEqualsAndHashcode() {
        Long bizQRId = 12L;
        String addressLine1 = "addressLine1";
        String addressLine2 = "addressLine2";
        String businessType = "businessType";
        String fyndrHandle = "fyndrHandle";
        String city = "city";
        String country = "country";
        String postalCode = "postalCode";
        String state = "state";

        BusinessDTO businessDTO1 = buildBusinessDTO(bizQRId, addressLine1, addressLine2, businessType, fyndrHandle,
                city, country, postalCode, state, AccountStatus.ACTIVE);

        assertEquals(businessDTO1, businessDTO1);
        assertEquals(businessDTO1.hashCode(), businessDTO1.hashCode());

        assertNotEquals(businessDTO1, new Object());

        BusinessDTO businessDTO2 = buildBusinessDTO(bizQRId, addressLine1, addressLine2, businessType, fyndrHandle,
                city, country, postalCode, state, AccountStatus.ACTIVE);
        assertEquals(businessDTO1, businessDTO2);
        assertEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2, businessType, fyndrHandle, city, country,
                postalCode, state, AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1 + " ", addressLine2, businessType, fyndrHandle, city, country,
                postalCode, state, AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2 + " ", businessType, fyndrHandle, city, country,
                postalCode, state, AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2, businessType + " ", fyndrHandle, city, country,
                postalCode, state, AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2, businessType, fyndrHandle + " ", city, country,
                postalCode, state, AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2, businessType, fyndrHandle, city + " ", country,
                postalCode, state, AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2, businessType, fyndrHandle, city, country + " ",
                postalCode, state, AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2, businessType, fyndrHandle, city, country,
                postalCode + " ", state, AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2, businessType, fyndrHandle, city, country,
                postalCode, state + " ", AccountStatus.ACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO2 = buildBusinessDTO(45L, addressLine1, addressLine2, businessType, fyndrHandle, city, country,
                postalCode, state, AccountStatus.INACTIVE);
        assertNotEquals(businessDTO1, businessDTO2);
        assertNotEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

        businessDTO1 = new BusinessDTO();
        businessDTO2 = new BusinessDTO();
        assertEquals(businessDTO1, businessDTO2);
        assertEquals(businessDTO1.hashCode(), businessDTO2.hashCode());

    }

    private BusinessDTO buildBusinessDTO(Long bizQRId, String addressLine1, String addressLine2, String businessType,
            String fyndrHandle, String city, String country, String postalCode, String state, AccountStatus accountStatus) {

        BusinessDTO businessDTO = new BusinessDTO();

        businessDTO.setBizQRId(bizQRId);
        businessDTO.setAddressLine1(addressLine1);
        businessDTO.setAddressLine2(addressLine2);
        businessDTO.setBusinessType(businessType);
        businessDTO.setFyndrHandle(fyndrHandle);
        businessDTO.setCity(city);
        businessDTO.setCountry(country);
        businessDTO.setPostalCode(postalCode);
        businessDTO.setState(state);
        businessDTO.setAccountStatus(accountStatus);

        return businessDTO;
    }
}
