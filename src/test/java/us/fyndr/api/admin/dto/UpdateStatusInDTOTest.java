package us.fyndr.api.admin.dto;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.dbo.AccountStatus;

import static org.junit.jupiter.api.Assertions.*;

public class UpdateStatusInDTOTest {

    @Test
    public void testGetterAndSetter() {
        UpdateStatusInDTO updateStatusInDTO = new UpdateStatusInDTO();

        assertNull(updateStatusInDTO.getAccountStatus());
        AccountStatus accountStatus = AccountStatus.ACTIVE;
        updateStatusInDTO.setAccountStatus(accountStatus);

        assertEquals(accountStatus, updateStatusInDTO.getAccountStatus());

    }

    @Test
    public void testToString() {
        UpdateStatusInDTO updateStatusInDTO = new UpdateStatusInDTO();

        AccountStatus accountStatus = AccountStatus.ACTIVE;
        updateStatusInDTO.setAccountStatus(accountStatus);

        assertEquals("UpdateStatusInDTO [accountStatus=ACTIVE]", updateStatusInDTO.toString());

    }

    @Test
    public void testEqualsAndHashcode() {
        AccountStatus accountStatus = AccountStatus.ACTIVE;

        UpdateStatusInDTO updateStatusInDTO1 = buildupdateStatusOutDTO(accountStatus);

        assertEquals(updateStatusInDTO1, updateStatusInDTO1);
        assertEquals(updateStatusInDTO1.hashCode(), updateStatusInDTO1.hashCode());

        assertNotEquals(updateStatusInDTO1, new Object());

        UpdateStatusInDTO updateStatusInDTO2 = buildupdateStatusOutDTO(accountStatus);
        assertEquals(updateStatusInDTO1, updateStatusInDTO2);
        assertEquals(updateStatusInDTO1.hashCode(), updateStatusInDTO2.hashCode());

        updateStatusInDTO2 = buildupdateStatusOutDTO(AccountStatus.DELETED);
        assertNotEquals(updateStatusInDTO1, updateStatusInDTO2);
        assertNotEquals(updateStatusInDTO1.hashCode(), updateStatusInDTO2.hashCode());

        updateStatusInDTO1 = new UpdateStatusInDTO();
        updateStatusInDTO2 = new UpdateStatusInDTO();
        assertEquals(updateStatusInDTO1, updateStatusInDTO2);

        assertEquals(updateStatusInDTO1.hashCode(), updateStatusInDTO2.hashCode());
    }

    private UpdateStatusInDTO buildupdateStatusOutDTO(AccountStatus accountStatus) {
        UpdateStatusInDTO updateStatusInDTO = new UpdateStatusInDTO();

        updateStatusInDTO.setAccountStatus(accountStatus);

        return updateStatusInDTO;
    }
}
