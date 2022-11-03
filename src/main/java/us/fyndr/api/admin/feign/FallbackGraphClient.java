package us.fyndr.api.admin.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.Response;

/**
 * FallbackGraphClient class contain fallback method to prevent any failure
 * while accessing the graph update profile api.
 *
 */
@Component
public class FallbackGraphClient implements GraphClient {

    /**
     * The logger used to log messages for a specific system.
     */
    private static Logger logger = LoggerFactory.getLogger(FallbackGraphClient.class);

    /**
     * SendPatchRequest is a fallback method to prevent failure and return proper
     * exception.
     */
    @Override
    public Response sendPatchRequest(final String payload, final String authToken) {
            logger.info("Unable to reach graph update profile service, falling back");
            return Response.builder().build();
        }
}
