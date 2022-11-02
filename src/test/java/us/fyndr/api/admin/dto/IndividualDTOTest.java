package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.dbo.AccountStatus;

public class IndividualDTOTest {

    @Test
    public void testGetterAndSetter() {
        IndividualDTO individualDTO = new IndividualDTO();

        assertNull(individualDTO.getCodeMasterQrId());
        Long codeMasterQrId = 34L;
        individualDTO.setCodeMasterQrId(codeMasterQrId);
        assertEquals(codeMasterQrId, individualDTO.getCodeMasterQrId());

        assertNull(individualDTO.getYob());
        String yob = "yob";
        individualDTO.setYob(yob);
        assertEquals(yob, individualDTO.getYob());

        assertNull(individualDTO.getGender());
        String gender = "gender";
        individualDTO.setGender(gender);
        assertEquals(gender, individualDTO.getGender());

        assertNull(individualDTO.getCountry());
        String country = "country";
        individualDTO.setCountry(country);
        assertEquals(country, individualDTO.getCountry());

        assertNull(individualDTO.getCity());
        String city = "city";
        individualDTO.setCity(city);
        assertEquals(city, individualDTO.getCity());

        assertNull(individualDTO.getFyndrHandle());
        String fyndrHandle = "fyndrHandle";
        individualDTO.setFyndrHandle(fyndrHandle);
        assertEquals(fyndrHandle, individualDTO.getFyndrHandle());

        assertNull(individualDTO.getAccountStatus());
        individualDTO.setAccountStatus(AccountStatus.ACTIVE);
        assertEquals(AccountStatus.ACTIVE, individualDTO.getAccountStatus());
    }

    @Test
    public void testToString() {
        IndividualDTO individualDTO = new IndividualDTO();

        long codeMasterQrId = 34;
        individualDTO.setCodeMasterQrId(codeMasterQrId);

        String yob = "yob";
        individualDTO.setYob(yob);

        String gender = "gender";
        individualDTO.setGender(gender);

        String country = "country";
        individualDTO.setCountry(country);

        String city = "city";
        individualDTO.setCity(city);

        String fyndrHandle = "fyndrHandle";
        individualDTO.setFyndrHandle(fyndrHandle);

        individualDTO.setAccountStatus(AccountStatus.ACTIVE);

        assertEquals(
                "IndividualDTO [codeMasterQrId=34, yob=yob, gender=gender, "
                        + "country=country, city=city, fyndrHandle=fyndrHandle, accountStatus=ACTIVE]",
                individualDTO.toString());
    }

    @Test
    public void testEqualsAndHashcode() {
        Long codeMasterQrId = 34L;
        String yob = "yob";
        String gender = "gender";
        String country = "country";
        String city = "city";
        String fyndrHandle = "fyndrHandle";

        IndividualDTO individualDTO1 = buildIndividualDTO(codeMasterQrId, yob, gender, country, city, fyndrHandle,
                AccountStatus.ACTIVE);

        assertEquals(individualDTO1, individualDTO1);
        assertEquals(individualDTO1.hashCode(), individualDTO1.hashCode());

        assertNotEquals(individualDTO1, new Object());

        IndividualDTO individualDTO2 = buildIndividualDTO(codeMasterQrId, yob, gender, country, city, fyndrHandle,
                AccountStatus.ACTIVE);
        assertEquals(individualDTO1, individualDTO2);
        assertEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

        individualDTO2 = buildIndividualDTO(12L, yob, gender, country, city, fyndrHandle, AccountStatus.ACTIVE);
        assertNotEquals(individualDTO1, individualDTO2);
        assertNotEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

        individualDTO2 = buildIndividualDTO(codeMasterQrId, yob + " ", gender, country, city, fyndrHandle,
                AccountStatus.ACTIVE);
        assertNotEquals(individualDTO1, individualDTO2);
        assertNotEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

        individualDTO2 = buildIndividualDTO(codeMasterQrId, yob, gender + " ", country, city, fyndrHandle,
                AccountStatus.ACTIVE);
        assertNotEquals(individualDTO1, individualDTO2);
        assertNotEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

        individualDTO2 = buildIndividualDTO(codeMasterQrId, yob, gender, country + " ", city, fyndrHandle,
                AccountStatus.ACTIVE);
        assertNotEquals(individualDTO1, individualDTO2);
        assertNotEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

        individualDTO2 = buildIndividualDTO(codeMasterQrId, yob, gender, country, city + " ", fyndrHandle,
                AccountStatus.ACTIVE);
        assertNotEquals(individualDTO1, individualDTO2);
        assertNotEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

        individualDTO2 = buildIndividualDTO(codeMasterQrId, yob, gender, country, city, fyndrHandle + " ",
                AccountStatus.ACTIVE);
        assertNotEquals(individualDTO1, individualDTO2);
        assertNotEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

        individualDTO2 = buildIndividualDTO(codeMasterQrId, yob, gender, country, city, fyndrHandle,
                AccountStatus.DELETED);
        assertNotEquals(individualDTO1, individualDTO2);
        assertNotEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

        individualDTO1 = new IndividualDTO();
        individualDTO2 = new IndividualDTO();
        assertEquals(individualDTO1, individualDTO2);
        assertEquals(individualDTO1.hashCode(), individualDTO2.hashCode());

    }

    private IndividualDTO buildIndividualDTO(Long codeMasterQrId, String yob, String gender, String country,
            String city, String fyndrHandle, AccountStatus accountStatus) {
        IndividualDTO individualDTO = new IndividualDTO();

        individualDTO.setCodeMasterQrId(codeMasterQrId);
        individualDTO.setYob(yob);
        individualDTO.setGender(gender);
        individualDTO.setCountry(country);
        individualDTO.setCity(city);
        individualDTO.setFyndrHandle(fyndrHandle);
        individualDTO.setAccountStatus(accountStatus);
        return individualDTO;
    }
}
