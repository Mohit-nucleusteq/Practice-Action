package us.fyndr.api.admin.token;

import org.springframework.cloud.openfeign.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import us.fyndr.api.admin.exception.TokenServiceException;
import us.fyndr.api.admin.exception.UnauthorizedException;
import us.fyndr.api.admin.model.FetchUserTokenRequest;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;

@FeignClient(name = "tokenClient", url = "${token.url}",
             fallbackFactory = FallbackTokenServiceClientFactory.class,
             configuration = FeignClientConfiguration.class)
public interface TokenServiceClient {

    /**
     * @param authorizationToken
     * @return Response
     * @throws TokenServiceException
     */
    @RequestLine("GET /v1/token/fetchData")
    @Headers("Authorization: {token}")
    TokenUser fetchTokenData(@Param("token") String authorizationToken) throws TokenServiceException;

    /**
     * @param fetchUserTokenRequest
     * @param authorizationToken
     * @return FetchUserTokenResponse
     * @throws UnauthorizedException
     * @throws TokenServiceException
     */
    @RequestLine("POST /v1/token/admin/fetchUserToken")
    @Headers("Authorization: {token}")
    FetchUserTokenResponse fetchUserToken(
            FetchUserTokenRequest fetchUserTokenRequest,
            @Param("token") String authorizationToken)
            throws UnauthorizedException, TokenServiceException;

}
