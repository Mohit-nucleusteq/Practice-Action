/**
 * @author Prerna Goyal
 */
package us.fyndr.api.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.exception.TokenServiceException;
import us.fyndr.api.admin.exception.UnauthorizedException;
import us.fyndr.api.admin.model.FetchUserTokenRequest;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;
import us.fyndr.api.admin.repository.IndividualRepository;
import us.fyndr.api.admin.token.TokenServiceClient;

import java.util.Base64;

public class TokenServiceTest {

    @Mock
    private TokenServiceClient tokenServiceClient;

    @Mock
    private IndividualRepository individualRepository;

    @InjectMocks
    private TokenService tokenService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMasqueradeUser() throws UnauthorizedException, IOException, TokenServiceException {

        String authToken = "authToken";

        Long objId = 5L;

        Individual individual = new Individual();

        individual.setEmail("email");

        when(individualRepository.findById(objId))
                .thenReturn(Optional.of(individual));

        FetchUserTokenRequest fetchUserTokenRequest = new FetchUserTokenRequest(
                Base64.getEncoder().encodeToString(individual.getEmail().getBytes(StandardCharsets.UTF_8)));

        TokenUser tokenUser = new TokenUser();
        tokenUser.setEmail("email");
        tokenUser.setObjId(1);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("userEntity");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);

        FetchUserTokenResponse mockFetchUserTokenResponse = new FetchUserTokenResponse();
        mockFetchUserTokenResponse.setAccessCode("accessCode");
        mockFetchUserTokenResponse.setAccessCodeExpiry("2000");
        mockFetchUserTokenResponse.setTokenUser(tokenUser);

        when(tokenServiceClient.fetchUserToken(fetchUserTokenRequest,
                authToken)).thenReturn(mockFetchUserTokenResponse);

        FetchUserTokenResponse actualTokenResponse = tokenService
                .masqueradeUser(objId, authToken);

        assertEquals(mockFetchUserTokenResponse, actualTokenResponse);
    }

}
