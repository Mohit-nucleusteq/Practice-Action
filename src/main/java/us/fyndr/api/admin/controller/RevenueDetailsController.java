package us.fyndr.api.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.dto.RevenueDetailsInDTO;
import us.fyndr.api.admin.service.RevenueService;
import us.fyndr.api.admin.util.URLConstants;

/**
 * RevenueDetailsController for fetching revenue statitics details.
 *
 */
@RestController
@RequestMapping(URLConstants.REVENUE_DETAILS_URL)
public class RevenueDetailsController {

    /**
     * the revenueService object.
     * */
    @Autowired
    private RevenueService revenueService;

    /**
     * Logger variable.
     * */
    private static final Logger LOGGER = LoggerFactory.getLogger(RevenueDetailsController.class);

    /**
     * revenueDetails method shows details related to revenue of businesses.
     * @param revenueDetailsInDTO - input dto for filtering results
     * @param pgStart - page start
     * @param pgSize - page size for results on a page
     * @return ResponseOutDTO - contains success field and details of revenue
     */
    @PostMapping
    public ResponseOutDTO revenueDetails(@RequestBody final RevenueDetailsInDTO revenueDetailsInDTO,
            @RequestParam(name = "pgStart", required = false, defaultValue = "1") final String pgStart,
            @RequestParam(name = "pgSize", required = false, defaultValue = "10") final String pgSize) {

        int pageStart = Integer.parseInt(pgStart);
        pageStart = pageStart <= 0 ? 1 : pageStart;

        LOGGER.info("Request received to show revenue details for: {}", revenueDetailsInDTO.toString());

        return new ResponseOutDTO(true,
                revenueService.revenueDetails(pageStart - 1, Integer.parseInt(pgSize), revenueDetailsInDTO));
    }

}
