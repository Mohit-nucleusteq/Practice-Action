package us.fyndr.api.admin.exception;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import feign.FeignException;
import us.fyndr.api.admin.model.ErrorResponse;
import us.fyndr.api.admin.util.ErrorConstants;

@ControllerAdvice
public class AdminExceptionHandler {

    /***
     * @param unauthorizedException
     * @return ResponseEntity of ErrorResponse type
     */

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponse> unauthorizedException(
            final UnauthorizedException unauthorizedException) {

        ErrorResponse errorReponse = new ErrorResponse(Instant.now(),
                HttpStatus.UNAUTHORIZED.toString(),
                unauthorizedException.getMessage(),
                ErrorConstants.UNAUTHORIZED_EXCEPTION);

        return new ResponseEntity<>(errorReponse, HttpStatus.UNAUTHORIZED);
    }

    /***
     * @param methodArgumentNotValidException
     * @return ResponseEntity of ErrorResponse type
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            final MethodArgumentNotValidException methodArgumentNotValidException) {

        List<String> errorReponse = new ArrayList<>();

        for (ObjectError objectError : methodArgumentNotValidException
                .getBindingResult().getAllErrors()) {
            errorReponse.add(objectError.getDefaultMessage());
        }

        ErrorResponse errorDetails = new ErrorResponse(Instant.now(),
                HttpStatus.BAD_REQUEST.toString(), errorReponse,
                HttpStatus.BAD_REQUEST.toString());
        return new ResponseEntity<ErrorResponse>(errorDetails,
                HttpStatus.BAD_REQUEST);
    }

    /***
     * @param tokenServiceException
     * @return ResponseEntity of ErrorResponse type
     */

    @ExceptionHandler(TokenServiceException.class)
    public ResponseEntity<ErrorResponse> tokenServiceException(
            final TokenServiceException tokenServiceException) {

        ErrorResponse errorReponse = new ErrorResponse(Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                tokenServiceException.getMessage(),
                ErrorConstants.FEIGN_EXCEPTION);

        return new ResponseEntity<>(errorReponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /***
     * @param feignException
     * @return ResponseEntity of ErrorResponse type
     */
    @ExceptionHandler(FeignException.class)
    public ResponseEntity<ErrorResponse> handleFeignException(
            final FeignException feignException) {

        ErrorResponse errorReponse = new ErrorResponse(Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                ErrorConstants.FEIGN_EXCEPTION,
                feignException.getCause().toString());

        return new ResponseEntity<>(errorReponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
