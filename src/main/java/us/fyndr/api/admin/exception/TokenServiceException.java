/**
 * @author Prerna Goyal
 *
 */
package us.fyndr.api.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TokenServiceException extends Exception {

    /**
     * Serial Version Id.
     */
    private static final long serialVersionUID = 3555917544093150908L;

    /**
     * @param message contains the message due to which exception occured
     * */
    public TokenServiceException(final String message) {
        super(message);
      }
}
