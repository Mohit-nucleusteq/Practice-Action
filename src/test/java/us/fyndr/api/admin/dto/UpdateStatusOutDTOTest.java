package us.fyndr.api.admin.dto;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.dbo.AccountStatus;

public class UpdateStatusOutDTOTest {
    @Test
    public void testGetterAndSetter() {
        UpdateStatusOutDTO updateStatusOutDTO = new UpdateStatusOutDTO();

        assertNull(updateStatusOutDTO.getSuccess());
        boolean success = true;
        updateStatusOutDTO.setSuccess(success);
        assertEquals(success, updateStatusOutDTO.getSuccess());

        assertNull(updateStatusOutDTO.getAccountStatus());
        AccountStatus accountStatus = AccountStatus.ACTIVE;
        updateStatusOutDTO.setAccountStatus(accountStatus);
        assertEquals(accountStatus, updateStatusOutDTO.getAccountStatus());

        assertNull(updateStatusOutDTO.getMessage());
        String message = "message";
        updateStatusOutDTO.setMessage(message);
        assertEquals(message, updateStatusOutDTO.getMessage());

    }

    @Test
    public void testToString() {
        UpdateStatusOutDTO updateStatusOutDTO = new UpdateStatusOutDTO();

        boolean success = true;
        updateStatusOutDTO.setSuccess(success);

        AccountStatus accountStatus = AccountStatus.ACTIVE;
        updateStatusOutDTO.setAccountStatus(accountStatus);

        String message = "message";
        updateStatusOutDTO.setMessage(message);

        assertEquals("UpdateStatusOutDTO [success=true, accountStatus=ACTIVE, message=message]",
                updateStatusOutDTO.toString());

    }

    @Test
    public void testEqualsAndHashcode() {
        boolean success = true;
        AccountStatus accountStatus = AccountStatus.ACTIVE;
        String message = "message";

        UpdateStatusOutDTO updateStatusOutDTO1 = buildupdateStatusOutDTO(success, accountStatus, message);

        assertEquals(updateStatusOutDTO1, updateStatusOutDTO1);
        assertEquals(updateStatusOutDTO1.hashCode(), updateStatusOutDTO1.hashCode());
        assertNotEquals(updateStatusOutDTO1, new Object());
        UpdateStatusOutDTO updateStatusOutDTO2 = buildupdateStatusOutDTO(success, accountStatus, message);
        assertEquals(updateStatusOutDTO1, updateStatusOutDTO2);
        assertEquals(updateStatusOutDTO1.hashCode(), updateStatusOutDTO2.hashCode());
        updateStatusOutDTO2 = buildupdateStatusOutDTO(false, accountStatus, message);
        assertNotEquals(updateStatusOutDTO1, updateStatusOutDTO2);
        assertNotEquals(updateStatusOutDTO1.hashCode(), updateStatusOutDTO2.hashCode());
        updateStatusOutDTO2 = buildupdateStatusOutDTO(true, AccountStatus.DELETED, message);
        assertNotEquals(updateStatusOutDTO1, updateStatusOutDTO2);
        assertNotEquals(updateStatusOutDTO1.hashCode(), updateStatusOutDTO2.hashCode());
        updateStatusOutDTO2 = buildupdateStatusOutDTO(true, accountStatus, message + " ");
        assertNotEquals(updateStatusOutDTO1, updateStatusOutDTO2);
        assertNotEquals(updateStatusOutDTO1.hashCode(), updateStatusOutDTO2.hashCode());
        updateStatusOutDTO1 = new UpdateStatusOutDTO();
        updateStatusOutDTO2 = new UpdateStatusOutDTO();
        assertEquals(updateStatusOutDTO1, updateStatusOutDTO2);
        assertEquals(updateStatusOutDTO1.hashCode(), updateStatusOutDTO2.hashCode());

    }

    private UpdateStatusOutDTO buildupdateStatusOutDTO(boolean success, AccountStatus accountStatus, String message) {
        UpdateStatusOutDTO updateStatusOutDTO = new UpdateStatusOutDTO();

        updateStatusOutDTO.setSuccess(success);
        ;
        updateStatusOutDTO.setAccountStatus(accountStatus);
        ;
        updateStatusOutDTO.setMessage(message);
        ;

        return updateStatusOutDTO;
    }
}
