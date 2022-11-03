/**
 * @author Prerna Goyal
 *
 */
package us.fyndr.api.admin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.exception.TokenServiceException;
import us.fyndr.api.admin.exception.UnauthorizedException;
import us.fyndr.api.admin.model.FetchUserTokenRequest;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.repository.IndividualRepository;
import us.fyndr.api.admin.token.TokenServiceClient;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * This class handles all the token related task.
 * */
@Service
public class TokenService {

    /**
     * This object is used to call token service related methods.
     */
    @Autowired
    private TokenServiceClient tokenServiceClient;

    /**
     * This object is used to call IndividualRepository methods.
     * */
    @Autowired
    private IndividualRepository individualRepository;

    /**
     * This method masquerades admin user as individual or business user.
     * @param objId contains object id of user
     * @param authToken Authorization token of an user
     * @return TokenResponse return access token, expiry and user information
     * @throws UnauthorizedException
     *
     */
    public FetchUserTokenResponse masqueradeUser(final Long objId,
            final String authToken) throws UnauthorizedException, TokenServiceException {

        Individual individual = individualRepository.findById(objId).get();

        String encodedEmail = Base64.getEncoder().encodeToString(individual.getEmail().getBytes(StandardCharsets.UTF_8));

        FetchUserTokenResponse fetchUserTokenResponse = tokenServiceClient
                .fetchUserToken(new FetchUserTokenRequest(encodedEmail), authToken);

        return fetchUserTokenResponse;
    }
}
