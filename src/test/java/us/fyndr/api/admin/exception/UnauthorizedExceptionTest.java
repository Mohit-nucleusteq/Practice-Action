package us.fyndr.api.admin.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.util.ErrorConstants;

class UnauthorizedExceptionTest {

    @Test
    void testUnauthorizedException(){

       UnauthorizedException unauthorizedException = new UnauthorizedException(ErrorConstants.UNAUTHORIZED_EXCEPTION);
       
       assertEquals(ErrorConstants.UNAUTHORIZED_EXCEPTION, unauthorizedException.getMessage());
    }

}
