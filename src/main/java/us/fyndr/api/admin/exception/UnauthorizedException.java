/**
 * @author Prerna Goyal
 *
 */
package us.fyndr.api.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * This class contains Unauthorized exception which return 401
 * Unauthorized HttpStatus.
 *
 */

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends Exception {

    /**
     * Serial Version Id.
     */
    private static final long serialVersionUID = 3555917544093150908L;

    /**
     * @param message contains the message due to which exception occured
     * */
    public UnauthorizedException(final String message) {
        super(message);
      }
}
