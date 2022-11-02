/**
 * @author Prerna Goyal
 *
 */
package us.fyndr.api.admin.token;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.core.JsonProcessingException;

import feign.FeignException;
import feign.Request;
import feign.Request.HttpMethod;
import feign.RequestTemplate;
import feign.Response;
import us.fyndr.api.admin.util.ErrorConstants;

public class TokenServiceErrorDecoderTest {

    private TokenServiceErrorDecoder tokenServiceErrorDecoder;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        tokenServiceErrorDecoder = new TokenServiceErrorDecoder();

    }

    @Test
    public void testUnauthorizedDecode() throws JsonProcessingException {

        Map<String, Collection<String>> headers = new HashMap<String, Collection<String>>();
        RequestTemplate requestTemplate = new RequestTemplate();

        Request request = Request.create(HttpMethod.POST, "/v1/token/admin/fetchUserToken", headers, null, requestTemplate );

        Response response = Response.builder()
                .status(ErrorConstants.HTTP_STATUS_UNAUTHORIZED)
                .reason("Unauthorized User")
                .headers(headers)
                .body("", StandardCharsets.UTF_8)
                .request(request)
                .build();

        ResponseStatusException exception = (ResponseStatusException)tokenServiceErrorDecoder.decode("", response);

        assertEquals(HttpStatus.UNAUTHORIZED, exception.getStatus());
        assertEquals("Only users with active and inactive status can be masquerade.", exception.getReason()); 
    }

    @Test
    public void testDefaultDecode() throws JsonProcessingException {

        MockitoAnnotations.initMocks(this);

        Map<String, Collection<String>> headers = new HashMap<String, Collection<String>>();
        RequestTemplate requestTemplate = new RequestTemplate();

        Request request = Request.create(HttpMethod.POST, "/v1/token/admin/fetchUserToken", headers, null, requestTemplate );

        Response response = Response.builder()
                .status(500)
                .reason("Internal Server Error")
                .headers(headers)
                .body("", StandardCharsets.UTF_8)
                .request(request)
                .build();

        Exception exception = tokenServiceErrorDecoder.decode("", response);
        assertThat(exception).isInstanceOf(FeignException.class);

    }

}
