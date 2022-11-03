/**
 * @author Prerna Goyal
 */
package us.fyndr.api.admin.token;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import us.fyndr.api.admin.exception.TokenServiceException;
import us.fyndr.api.admin.exception.UnauthorizedException;
import us.fyndr.api.admin.model.FetchUserTokenRequest;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;

/**
 * This class handles fallback errors if any error thrown from {@link TokenServiceClient}.
 */
@Component
public class FallbackTokenServiceClient implements TokenServiceClient {

    /**
     * This object is used for cause of error.
     */
    private Throwable cause;

    /**
     * This variable is used for logging purpose.
     * */
    private static final Logger LOGGER = LoggerFactory.getLogger(FallbackTokenServiceClient.class);

    /**
     * Parameterized Constructor.
     * @param cause
     */
    public FallbackTokenServiceClient(final Throwable cause) {
        this.cause = cause;
    }

    /**
     * Overriding fetchUserToken for fallback error.
     * This method fetchs the cause of error and stores the statuscode in fetchUserTokenResponse.
     * The cause is fetched from {@link TokenServiceErrorDecoder}
     * though TokenServiceException is thrown but internally throwing FeignException
     * @param fetchUserTokenRequest token request contains objId of user
     * @param authorizationToken
     * @return fetchUserTokenResponse response from the fetchUserToken
     * @throws UnauthorizedException
     * @throws TokenServiceException
     */
    @Override
    public FetchUserTokenResponse fetchUserToken(
            final FetchUserTokenRequest fetchUserTokenRequest,
            final String authorizationToken) throws UnauthorizedException, TokenServiceException {

        if (cause instanceof ResponseStatusException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) cause;
            if (responseStatusException.getStatus().value() == HttpStatus.UNAUTHORIZED.value()) {
                throw new UnauthorizedException(
                        responseStatusException.getReason());
            }
        } else {
            LOGGER.info("Unable to reach token service");
            throw new TokenServiceException(cause.getMessage());
        }
        return new FetchUserTokenResponse();
    }

    /**
     * @param authorizationToken
     * @return TokenUser
     * @throws TokenServiceException
     */
    @Override
    public TokenUser fetchTokenData(final String authorizationToken) throws TokenServiceException {

        LOGGER.info("Unable to reach token service");
        throw new TokenServiceException(cause.getMessage());
    }
}
