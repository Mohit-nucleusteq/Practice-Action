package us.fyndr.api.admin.controller;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import us.fyndr.api.admin.dto.CampaignDetailsInDTO;
import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.service.CampaignDetailsService;
import us.fyndr.api.admin.util.URLConstants;

@RestController
@RequestMapping(URLConstants.CAMPAIGN_DETAILS_URL)
public class CampaignDetailsController {
    /**
     * The Campaign Details Service.
     */
    @Autowired
    private CampaignDetailsService campaignDetailsService;

    /**
     * The logger variable to log information or errors related to CampaignDetailsController class.
     */
    private static final Logger LOGGER = LoggerFactory
            .getLogger(CampaignDetailsController.class);

    /**
     * campaignDetails method shows details related to campaigns of all businesses.
     * @param campaignDetailsInDTO - input dto for filtering results
     * @param pgStart              - page start
     * @param pgSize               - page size for results on a page
     * @return ResponseOutDTO - contains success field and details of campaigns
     */
    @PostMapping
    public ResponseOutDTO campaignDetails(
            @RequestBody @Valid final CampaignDetailsInDTO campaignDetailsInDTO,
            @RequestParam(name = "pgStart", required = false, defaultValue = "1") final String pgStart,
            @RequestParam(name = "pgSize", required = false, defaultValue = "10") final String pgSize) {

        int pageStart = Integer.parseInt(pgStart);
        pageStart = pageStart <= 0 ? 1 : pageStart;
        LOGGER.info("Request received to show details of campaigns : {}",
                campaignDetailsInDTO.toString());
        return new ResponseOutDTO(true,
                campaignDetailsService.getCampaignDetails(pageStart - 1,
                        Integer.parseInt(pgSize), campaignDetailsInDTO));
    }
}
