package us.fyndr.api.admin.feign;

import org.springframework.cloud.openfeign.FeignClient;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import us.fyndr.api.admin.token.FeignClientConfiguration;

/**
 * GraphClient Interface is an feign client interface for graph update profile api.
 *
 */
@FeignClient(name = "graphClient", url = "${graph.url}",
        fallback = FallbackGraphClient.class, configuration = FeignClientConfiguration.class)
public interface GraphClient {

    /**
     * @param payload   is request payload for graph update profile api request
     * @param authToken contains authorization header required to access graph api
     * @return Response is an immutable response to an http invocation which only
     *         returns string content
     */
    @RequestLine(value = "PATCH profile")
    @Headers("Authorization: {token}")
    Response sendPatchRequest(String payload, @Param("token") String authToken);
}
