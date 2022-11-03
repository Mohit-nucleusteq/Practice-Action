package us.fyndr.api.admin.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.util.AdminConstants;

public class UserBadRequestExceptionTest {

    @Test
    void test() {
        UserBadRequestException userBadRequestException = new UserBadRequestException(
                AdminConstants.INVALID_INDIVIDUAL_STATUS);

        assertEquals(AdminConstants.INVALID_INDIVIDUAL_STATUS, userBadRequestException.getMessage());
    }
}
