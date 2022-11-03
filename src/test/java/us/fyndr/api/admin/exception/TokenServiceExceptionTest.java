package us.fyndr.api.admin.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import us.fyndr.api.admin.util.ErrorConstants;

class TokenServiceExceptionTest {

    @Test
    void test() {

        TokenServiceException tokenServiceException = new TokenServiceException(
                ErrorConstants.FEIGN_EXCEPTION);

        assertEquals(ErrorConstants.FEIGN_EXCEPTION, tokenServiceException.getMessage());
    }
}
