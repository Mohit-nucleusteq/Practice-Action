package us.fyndr.api.admin.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * UserBadRequestException class for handling exception when the required
 * information/header is missing from the request..
 *
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserBadRequestException extends Exception {

    /**
     * @param message to be printed while throwing exception
     */
    public UserBadRequestException(final String message) {
        super(message);
    }
}
