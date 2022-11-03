/**
*@author Prerna Goyal
 *
 */
package us.fyndr.api.admin.util;

/**
 * Constants class contains all the constants.
 */
public final class Constants {

    /**
     * A private constructor to resolve checkstyle errors.
     */
    private Constants() {

    }

    /**
     * The maximum attempts for retrying feign service call.
     */
    public static final int MAX_ATTEMPTS = 3;

    /**
     * The period for retrying feign service call.
     */
    public static final long PERIOD = 1000;

    /**
     * The max period for retrying feign service call.
     */
    public static final long MAX_PERIOD = 10000;

    /**
     * The variable using in statistics service.
     */
    public static final double HUNDRED = 100.0;
}
