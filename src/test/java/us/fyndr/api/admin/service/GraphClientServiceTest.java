package us.fyndr.api.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import feign.Request;
import feign.Request.HttpMethod;
import feign.RequestTemplate;
import feign.Response;
import us.fyndr.api.admin.feign.GraphClient;

public class GraphClientServiceTest {

    @Mock
    private GraphClient graphClient;

    @InjectMocks
    private GraphClientService graphClientService;

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendPatchRequestForStatusOK() {
        String payload = "payload";
        String authToken = "authToken";

        Map<String, Collection<String>> headers = new HashMap<String, Collection<String>>();
        RequestTemplate requestTemplate = new RequestTemplate();

        Request request = Request.create(HttpMethod.PATCH, "{graph.url}", headers, null, requestTemplate);

        Response response = Response.builder().status(HttpStatus.OK.value()).reason("Authorized User").headers(headers)
                .body("", StandardCharsets.UTF_8).request(request).build();

        Mockito.when(graphClient.sendPatchRequest(payload, authToken)).thenReturn(response);
        graphClientService.sendPatchRequest(payload, authToken);

        assertEquals(HttpStatus.OK.value(), response.status());

    }

    @Test
    public void testSendPatchRequestForStatusNotOK() {
        String payload = "payload";
        String authToken = "authToken";

        Map<String, Collection<String>> headers = new HashMap<String, Collection<String>>();
        RequestTemplate requestTemplate = new RequestTemplate();

        Request request = Request.create(HttpMethod.PATCH, "{graph.url}", headers, null, requestTemplate);

        Response response = Response.builder().status(HttpStatus.UNAUTHORIZED.value()).reason("Unauthorized User")
                .headers(headers).body("", StandardCharsets.UTF_8).request(request).build();

        Mockito.when(graphClient.sendPatchRequest(payload, authToken)).thenReturn(response);

        graphClientService.sendPatchRequest(payload, authToken);

    }

}
