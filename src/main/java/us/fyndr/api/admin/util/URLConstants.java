package us.fyndr.api.admin.util;

/**
 * @author saksh URLConstants class will store constant strings related to URL.
 */
public final class URLConstants {

    /**
     * A private constructor to resolve checkstyle errors.
     */
    private URLConstants() {

    }

    /**
     * The Base URL of Admin Service.
     */
    public static final String BASE_URL = "/admin";

    /**
     * The URL of UserController.
     */
    public static final String USER_URL = BASE_URL + "/user";

    /**
     * The URL of StatisticsController.
     */
    public static final String STATISTICS_URL = BASE_URL + "/statistics";

    /**
     * The URL of RevenueDetailsController.
     */
    public static final String REVENUE_DETAILS_URL = BASE_URL + "/revenue";

    /**
     * The URL of CampaignDetailsController.
     */
    public static final String CAMPAIGN_DETAILS_URL = BASE_URL + "/campaign";

}
