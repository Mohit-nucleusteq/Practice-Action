package us.fyndr.api.admin.exception;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.util.AdminConstants;
import us.fyndr.api.admin.util.ErrorConstants;

class UserNotFoundExceptionTest {

    @Test
    void test() {
        UserNotFoundException userNotFoundException = new UserNotFoundException(ErrorConstants.OBJECT_ID_NOT_FOUND);
        
        UserNotFoundException userNotFoundException1 = new UserNotFoundException(AdminConstants.USER_NOT_FOUND);
        
        assertEquals(ErrorConstants.OBJECT_ID_NOT_FOUND, userNotFoundException.getMessage());
        assertEquals(AdminConstants.USER_NOT_FOUND, userNotFoundException1.getMessage());
    }

}
