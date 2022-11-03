package us.fyndr.api.admin.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class PhoneNumberOutDTOTest {

    @Test
    public void testGetterAndSetter() {
        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();

        assertNull(phoneNumberOutDTO.getCountryCode());
        String countryCode = "+91";
        phoneNumberOutDTO.setCountryCode(countryCode);
        assertEquals(countryCode, phoneNumberOutDTO.getCountryCode());

        assertNull(phoneNumberOutDTO.getPhoneNumber());
        String phoneNo = "9999999999";
        phoneNumberOutDTO.setPhoneNumber(phoneNo);
        assertEquals(phoneNo, phoneNumberOutDTO.getPhoneNumber());
    }

    @Test
    public void testToString() {
        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();

        String countryCode = "+91";
        phoneNumberOutDTO.setCountryCode(countryCode);

        String phoneNo = "9999999999";
        phoneNumberOutDTO.setPhoneNumber(phoneNo);

        assertEquals("PhoneNumberOutDTO [countryCode=+91, phoneNumber=9999999999]",
                phoneNumberOutDTO.toString());
    }

    @Test
    public void testEqualAndHashCode() {
        String countryCode = "+91";
        String phoneNo = "9999999999";

        PhoneNumberOutDTO phoneNumberOutDTO1 = setData(countryCode, phoneNo);

        assertEquals(phoneNumberOutDTO1, phoneNumberOutDTO1);
        assertEquals(phoneNumberOutDTO1.hashCode(), phoneNumberOutDTO1.hashCode());

        assertNotEquals(phoneNumberOutDTO1, new Object());

        PhoneNumberOutDTO phoneNumberOutDTO2 = setData(countryCode, phoneNo);

        assertEquals(phoneNumberOutDTO2, phoneNumberOutDTO2);
        assertEquals(phoneNumberOutDTO2.hashCode(), phoneNumberOutDTO2.hashCode());

        phoneNumberOutDTO2 = setData(countryCode + " ", phoneNo);

        assertNotEquals(phoneNumberOutDTO1, phoneNumberOutDTO2);
        assertNotEquals(phoneNumberOutDTO1.hashCode(), phoneNumberOutDTO2.hashCode());

        phoneNumberOutDTO2 = setData(countryCode, phoneNo + " ");

        assertNotEquals(phoneNumberOutDTO1, phoneNumberOutDTO2);
        assertNotEquals(phoneNumberOutDTO1.hashCode(), phoneNumberOutDTO2.hashCode());

        phoneNumberOutDTO1 = new PhoneNumberOutDTO();
        phoneNumberOutDTO2 = new PhoneNumberOutDTO();

        assertEquals(phoneNumberOutDTO1, phoneNumberOutDTO2);
        assertEquals(phoneNumberOutDTO1.hashCode(), phoneNumberOutDTO2.hashCode());
    }

    private PhoneNumberOutDTO setData(String countryCode, String phoneNo) {
        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();

        phoneNumberOutDTO.setCountryCode(countryCode);
        phoneNumberOutDTO.setPhoneNumber(phoneNo);

        return phoneNumberOutDTO;
    }
}
