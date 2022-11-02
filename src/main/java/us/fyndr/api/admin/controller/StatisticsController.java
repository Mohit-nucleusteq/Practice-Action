package us.fyndr.api.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.service.StatisticsService;
import us.fyndr.api.admin.util.URLConstants;

/**
 * StatisticsController Class redirects API requests related to Admin Dashboard.
 */
@RestController
@RequestMapping(URLConstants.STATISTICS_URL)
public class StatisticsController {

    /**
     * The StatisticsService object.
     */
    @Autowired
    private StatisticsService statisticsService;

    /**
     * @return CampaignStatisticsOutDTO contains the fields which represents
     *         campaign statistics. getCampaignStatistics method gets the statistics
     *         of a campaign.
     */
    @GetMapping("/campaign")
    public ResponseOutDTO getCampaignStatistics() {
        return new ResponseOutDTO(true, statisticsService.getCampaignStatistics());
    }

    /**
     * @return UserStatisticsOutDTO contains the fields which represents campaign
     *         statistics.
     */
    @GetMapping("/user")
    public ResponseOutDTO getUserStatistics() {
        return new ResponseOutDTO(true, statisticsService.getUserStatistics());
    }

    /**
     * @param country
     * @return RevenueStasticsOutDto contains the fields which represent revenue
     *         statistics.
     */
    @GetMapping("/revenue")
    public ResponseOutDTO getRevenueStatistics(
            @RequestParam(name = "country", required = false, defaultValue = "US") final String country) {
        return new ResponseOutDTO(true, statisticsService.getRevenueStatistics(country));
    }
}
