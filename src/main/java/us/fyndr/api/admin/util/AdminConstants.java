package us.fyndr.api.admin.util;

/**
 * Constants class contains string constants and integer constants which can be
 * used in other classes to reduce code duplicacy & to handle MagicNumbers error
 * in checkstyle.
 *
 */
public final class AdminConstants {

    /**
     * deafult constructor for resolving checkstyle error.
     */
    private AdminConstants() {
    }

    /**
     * The Invalid Individual Status.
     */
    public static final String INVALID_INDIVIDUAL_STATUS = "Individual users can not have inactive account status";

    /**
     * The Fyndr Address.
     */
    public static final String FYNDR_ADDRESS = "Fyndr Inc. \r\n 3793 E Covey Lane\r\n \r\n Phoenix – AZ 85050";

    /**
     * The Fyndr Phone number.
     */
    public static final String FYNDR_PHONE_NO = "+1 9136382948";

    /**
     * The Fyndr Website.
     */
    public static final String FYNDR_WEBSITE = "www.fyndr.us";

    /**
     * The response after updating profile.
     */
    public static final String USER_UPDATED_SUCCESSFULLY = "Account status has been updated successfully";

    /**
     * The template for SNS notification.
     */
    public static final String ACCOUNT_STATUS_TEMPLATE = "account_status";

    /**
     * The Notification for SNS notification.
     */
    public static final String EMAIL_NOTIFICATION = "email-single";

    /**
     * The success code used in a HttpUtilMethod.
     */
    public static final int HTTP_SUCCESS_CODE = 200;

    /**
     * The user id.
     */
    public static final String USER_NOT_FOUND = "User is not registered with id";
}
