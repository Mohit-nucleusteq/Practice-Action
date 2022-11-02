package us.fyndr.api.admin.token;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import feign.Response;
import feign.codec.ErrorDecoder;
import us.fyndr.api.admin.util.ErrorConstants;

public class TokenServiceErrorDecoder implements ErrorDecoder {

    /**
     * object for decoding error.
     */
    private ErrorDecoder errorDecoder = new Default();

    /**
     * decode method to decode the error.
     * @param methodKey
     * @param response
     * @return errorDecode
     * @throws ResponseStatusException
     */
    @Override
    public Exception decode(final String methodKey, final Response response) {

        switch (response.status()) {
        case ErrorConstants.HTTP_STATUS_UNAUTHORIZED:

            return new ResponseStatusException(HttpStatus.UNAUTHORIZED,
                     ErrorConstants.MASQUERADE_USER_ERROR);

        default:
            return errorDecoder.decode(methodKey, response);
        }
    }
}
