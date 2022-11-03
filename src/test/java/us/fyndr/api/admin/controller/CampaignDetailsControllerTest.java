package us.fyndr.api.admin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import us.fyndr.api.admin.dto.CampaignDetailsInDTO;
import us.fyndr.api.admin.dto.CampaignDetailsOutDTO;
import us.fyndr.api.admin.dto.PagedCampaignDetailOutDTO;
import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.service.CampaignDetailsService;

public class CampaignDetailsControllerTest {

    /**
     * The Campaign Details Controller object.
     */
    @InjectMocks
    private CampaignDetailsController campaignDetailsController;

    /**
     * The Campaign Details Service object.
     */
    @Mock
    private CampaignDetailsService campaignDetailsService;

    /**
     * The MockMvc object.
     */
    private MockMvc mockMvc;

    /**
     * setUp method will set the initial configurations required to run this Junit
     * testcase.
     */
    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(campaignDetailsController).build();
    }

    @Test
    public void testSearchCampaignDetails() throws JsonProcessingException, Exception {

        CampaignDetailsInDTO campaignDetailsInDTO = new CampaignDetailsInDTO();
        campaignDetailsInDTO.setBusinessName("abc");
        campaignDetailsInDTO.setCountry("US");

        CampaignDetailsOutDTO campaignDetailsOutDTO = new CampaignDetailsOutDTO();
        campaignDetailsOutDTO.setActiveOffers(12);
        campaignDetailsOutDTO.setBusinessName("abc");
        campaignDetailsOutDTO.setCampaignName("cmpnName");
        campaignDetailsOutDTO.setCampaignType("cmpnType");
        campaignDetailsOutDTO.setEndDate(LocalDate.now());
        campaignDetailsOutDTO.setIndustryType("industryType");
        campaignDetailsOutDTO.setOfferSold(12);
        campaignDetailsOutDTO.setTotalOffers(11);
        campaignDetailsOutDTO.setTotalOfferSoldAmount(12.21);
        campaignDetailsOutDTO.setCurrency("USD");
        campaignDetailsOutDTO.setCurrencySymbol("$");

        List<CampaignDetailsOutDTO> campaignList = new ArrayList<>();
        campaignList.add(campaignDetailsOutDTO);

        PagedCampaignDetailOutDTO mockPagedCampaignDetailOutDTO = new PagedCampaignDetailOutDTO();
        mockPagedCampaignDetailOutDTO.setCampaignDetails(campaignList);
        mockPagedCampaignDetailOutDTO.setLast(true);
        mockPagedCampaignDetailOutDTO.setCount(5l);

        ResponseOutDTO responseOutDTO = new ResponseOutDTO(true, mockPagedCampaignDetailOutDTO);

        String pgStart = "1";
        String pgSize = "10";

        when(campaignDetailsService.getCampaignDetails(Integer.parseInt(pgStart) - 1, Integer.parseInt(pgSize),
                campaignDetailsInDTO)).thenReturn(mockPagedCampaignDetailOutDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/campaign")
                .contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(campaignDetailsInDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(responseOutDTO), mvcResult.getResponse().getContentAsString());

    }

}
