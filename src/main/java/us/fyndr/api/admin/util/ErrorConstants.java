/**
 * @author Prerna Goyal
 */
package us.fyndr.api.admin.util;

/**
 * Error Constant Interface contains all the error constants.
 */
public interface ErrorConstants {

    /**
     * UNAUTHORIZED_EXCEPTION.
     */
    String UNAUTHORIZED_EXCEPTION = "Unauthorized access";

    /**
     * INVALID_USER.
     */
    String INVALID_EMAIL_ID = "Email id does not exist or does not have valid status.";

    /**
     * Object Id Not Found Error Message .
     */
    String OBJECT_ID_NOT_FOUND = "Object Id %d not found";

    /**
     * UNAUTHORIZED_VALUE.
     */
    int HTTP_STATUS_UNAUTHORIZED = 401;

    /**
     * FEIGN_EXCEPTION message.
     * */
    String FEIGN_EXCEPTION = "Unable to connect to service.";

    /**
     * MASQUERADE_USER_ERROR.
     */
    String MASQUERADE_USER_ERROR = "Only users with active and inactive status can be masquerade.";
    /**
     * Method added to resolve checkstyle comment.
     */
    void doNothing();

}
