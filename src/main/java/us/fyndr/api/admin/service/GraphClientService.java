package us.fyndr.api.admin.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import feign.Response;
import us.fyndr.api.admin.feign.GraphClient;

/**
 * GraphCLientService class contains the method sendPatchRequest to access the
 * graph service through Feign client.
 *
 */
@Service
public class GraphClientService {

    /**
     * The GraphClient object.
     */
    @Autowired
    private GraphClient graphClient;

    /**
     * The logger variable to log information or error related to GraphClientService
     * class.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(GraphClientService.class);

    /**
     * @param payload   is a request body for update api in graph service
     * @param authToken stores authorization header value
     */
    public void sendPatchRequest(final String payload, final String authToken) {

        Response graphServiceResponse = graphClient.sendPatchRequest(payload, authToken);

        if (HttpStatus.OK.value() != graphServiceResponse.status()) {
            LOGGER.info("Error respose status: {}", graphServiceResponse.status());
            LOGGER.info("Error response message: {}", graphServiceResponse.body());
        }

        LOGGER.info("Graph respose status: {}", graphServiceResponse.status());
        LOGGER.info("graph response message: {}", graphServiceResponse.body());
        LOGGER.info("Graph request body: {}", payload);
    }
}
