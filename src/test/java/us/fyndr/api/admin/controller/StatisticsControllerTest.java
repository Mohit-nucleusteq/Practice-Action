package us.fyndr.api.admin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import us.fyndr.api.admin.dto.CampaignStatisticsOutDTO;
import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.dto.RevenueStatisticsOutDTO;
import us.fyndr.api.admin.dto.UserStatisticsOutDTO;
import us.fyndr.api.admin.service.StatisticsService;

/**
 * StatisticsControllerTest is the Junit class for StatisticsController Class.
 *
 */
class StatisticsControllerTest {

    /**
     * The StatisticsController object.
     */
    @InjectMocks
    StatisticsController statisticsController;

    /**
     * The StatisticsService object.
     */
    @Mock
    StatisticsService statisticsService;

    /**
     * The MockMvc object.
     */
    private MockMvc mockMvc;

    /**
     * The OBJECTMAPPER object.
     */
    private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

    /**
     * setUp method will set the initial configurations required to run this Junit
     * testcase.
     */
    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(statisticsController).build();
    }

    /**
     * @throws Exception testGetCampaignStatistics method tests the outDTO by
     *                   hitting the get API.
     */
    @Test
    public void testGetCampaignStatistics() throws Exception {

        CampaignStatisticsOutDTO campaignStatisticsOutDTO = new CampaignStatisticsOutDTO();
        campaignStatisticsOutDTO.setActive(280L);
        campaignStatisticsOutDTO.setOnline(150L);
        campaignStatisticsOutDTO.setInstore(60L);
        campaignStatisticsOutDTO.setAll(30L);

        when(statisticsService.getCampaignStatistics()).thenReturn(campaignStatisticsOutDTO);

        ResponseOutDTO responseOutDTO = new ResponseOutDTO(true, campaignStatisticsOutDTO);

        MvcResult mvcResult = mockMvc
                .perform(MockMvcRequestBuilders.get("/admin/statistics/campaign")
                        .accept(MediaType.APPLICATION_JSON_VALUE).contentType(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPPER.writeValueAsString(responseOutDTO), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetUserStatistics() throws Exception {
        UserStatisticsOutDTO userStatisticsOutDTO = new UserStatisticsOutDTO();
        userStatisticsOutDTO.setCustomer(20l);
        userStatisticsOutDTO.setMerchant(25l);
        userStatisticsOutDTO.setUser(30l);
        String uri = "/admin/statistics/user";

        when(statisticsService.getUserStatistics()).thenReturn(userStatisticsOutDTO);

        ResponseOutDTO responseOutDTO = new ResponseOutDTO(true, userStatisticsOutDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userStatisticsOutDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();

        assertEquals(OBJECTMAPPPER.writeValueAsString(responseOutDTO), responseBodyAsString);

    }

    @Test
    public void testGetRevenueStatistics() throws Exception {
        RevenueStatisticsOutDTO revenueStatisticsOutDTO = new RevenueStatisticsOutDTO();
        revenueStatisticsOutDTO.setCatalogueRevenue(353.35);
        revenueStatisticsOutDTO.setInteractionRevenue(242.522);
        revenueStatisticsOutDTO.setOfferRevenue(24.325);
        revenueStatisticsOutDTO.setPromotionalRevenue(252.25);
        revenueStatisticsOutDTO.setTotalRevenue(22.252);
        revenueStatisticsOutDTO.setCurrency("currency");
        revenueStatisticsOutDTO.setCurrencySymbol("currencySymbol");
        String authToken = "authToken";

        when(statisticsService.getRevenueStatistics("US")).thenReturn(revenueStatisticsOutDTO);

        ResponseOutDTO responseOutDTO = new ResponseOutDTO(true, revenueStatisticsOutDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/statistics/revenue")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
                .header("authorization", authToken)
                .content(new ObjectMapper().writeValueAsString(revenueStatisticsOutDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        String responseBodyAsString = mvcResult.getResponse().getContentAsString();

        assertEquals(OBJECTMAPPPER.writeValueAsString(responseOutDTO), responseBodyAsString);

    }

}
