/**
 * @author Prerna Goyal
 *
 */
package us.fyndr.api.admin.token;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import us.fyndr.api.admin.exception.TokenServiceException;
import us.fyndr.api.admin.exception.UnauthorizedException;
import us.fyndr.api.admin.model.FetchUserTokenRequest;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.util.ErrorConstants;

public class FallbackTokenServiceClientTest {

    @Mock
    Throwable cause;

    @Mock
    FetchUserTokenResponse mockFetchUserTokenResponse;

    @InjectMocks
    FallbackTokenServiceClient fallbackTokenServiceClient;
    
    @BeforeEach
    public void setup() {

        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void testExceptionFetchUserToken() throws UnauthorizedException, TokenServiceException {

        cause = new ResponseStatusException(HttpStatus.UNAUTHORIZED, ErrorConstants.INVALID_EMAIL_ID);

        fallbackTokenServiceClient = new FallbackTokenServiceClient(cause);

        String authToken = "authToken";
        String  email = "email";

        FetchUserTokenRequest fetchUserTokenRequest = new FetchUserTokenRequest(
                Base64.getEncoder().encodeToString(email.getBytes(StandardCharsets.UTF_8)));
      
        Exception exception = assertThrows(UnauthorizedException.class, () -> {
            fallbackTokenServiceClient.fetchUserToken(fetchUserTokenRequest, authToken);
        });

        assertEquals(ErrorConstants.INVALID_EMAIL_ID, exception.getMessage());
    }
    
    @Test
    public void testFetchUserToken() throws UnauthorizedException, TokenServiceException {

        cause = new ResponseStatusException(HttpStatus.BAD_GATEWAY, ErrorConstants.INVALID_EMAIL_ID);

        fallbackTokenServiceClient = new FallbackTokenServiceClient(cause);

        String authToken = "authToken";
        String  email = "email";

        FetchUserTokenRequest fetchUserTokenRequest = new FetchUserTokenRequest(
                Base64.getEncoder().encodeToString(email.getBytes()));
        
        FetchUserTokenResponse expectedFetchUserTokenResponse = new FetchUserTokenResponse();
        
        FetchUserTokenResponse fetchUserTokenResponse =  fallbackTokenServiceClient.fetchUserToken(fetchUserTokenRequest, authToken);

        assertEquals(expectedFetchUserTokenResponse, fetchUserTokenResponse);
    }

    @Test
    public void testOtherExceptionFetchUserToken() throws UnauthorizedException, TokenServiceException {

        cause = new Exception(ErrorConstants.FEIGN_EXCEPTION);
        fallbackTokenServiceClient = new FallbackTokenServiceClient(cause);

        String authToken = "authToken";
        String  email = "email";

        FetchUserTokenRequest fetchUserTokenRequest = new FetchUserTokenRequest(
                Base64.getEncoder().encodeToString(email.getBytes()));

        Exception exception = assertThrows(TokenServiceException.class, () -> {
            fallbackTokenServiceClient.fetchUserToken(fetchUserTokenRequest, authToken);
        });

        assertEquals(ErrorConstants.FEIGN_EXCEPTION, exception.getMessage());
    }

    @Test
    public void testFetchTokenData() throws TokenServiceException {

        String authToken = "authToken";

        cause = new Exception(ErrorConstants.FEIGN_EXCEPTION);
        fallbackTokenServiceClient = new FallbackTokenServiceClient(cause);

        Exception exception = assertThrows(TokenServiceException.class, () -> {
            fallbackTokenServiceClient.fetchTokenData(authToken);
        });

        assertEquals(ErrorConstants.FEIGN_EXCEPTION, exception.getMessage());

    }
}
