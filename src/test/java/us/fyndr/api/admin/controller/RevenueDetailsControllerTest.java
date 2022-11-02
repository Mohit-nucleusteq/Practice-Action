package us.fyndr.api.admin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import us.fyndr.api.admin.dto.PagedRevenueDetailsOutDTO;
import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.dto.RevenueDetailsInDTO;
import us.fyndr.api.admin.dto.RevenueDetailsOutDTO;
import us.fyndr.api.admin.service.RevenueService;

class RevenueDetailsControllerTest {

    @Mock
    private RevenueService revenueService;
    
    @InjectMocks
    private RevenueDetailsController revenueDetailsController;

    private MockMvc mockMvc;

    private static final ObjectMapper OBJECTMAPPER = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
       
    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(revenueDetailsController).build();
    }

    @Test
    public void testRevenueDetails() throws JsonProcessingException, Exception {


        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        String country = "country";
        String businessName = "businessName";
        LocalDate startDate = LocalDate.parse("2022-02-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse("2022-09-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        revenueDetailsInDTO.setCountry(country);
        revenueDetailsInDTO.setBusinessName(businessName);
        revenueDetailsInDTO.setStartDate(startDate);
        revenueDetailsInDTO.setEndDate(endDate);

        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(506.235);

        revenueDetailsOutDTO.setOffers(25.62156);
        revenueDetailsOutDTO.setInteraction(55.120);
        revenueDetailsOutDTO.setPromo(0.0);
        revenueDetailsOutDTO.setCatalog(0.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("#");

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);
        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(true);
        pagedRevenueDetailsOutDTO.setCount(5L);

        ResponseOutDTO responseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
        String pgStart = "0";
        String pgSize = "5";
        
        int pageStart = Integer.parseInt(pgStart);
        pageStart = pageStart <= 0 ? 1 : pageStart;
        
        when(revenueService.revenueDetails(pageStart - 1, Integer.parseInt(pgSize), revenueDetailsInDTO))
                .thenReturn(pagedRevenueDetailsOutDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/revenue?")
                                        .queryParam("pgStart", pgStart)
                                        .queryParam("pgSize", pgSize)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
                                        .content(OBJECTMAPPER.writeValueAsBytes(revenueDetailsInDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200, mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPER.writeValueAsString(responseOutDTO), mvcResult.getResponse().getContentAsString());

    }
    
    @Test
    public void testPageStartRevenueDetails() throws JsonProcessingException, Exception {

        RevenueDetailsInDTO revenueDetailsInDTO = new RevenueDetailsInDTO();
        String country = "country";
        String businessName = "businessName";
        LocalDate startDate = LocalDate.parse("2022-02-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        LocalDate endDate = LocalDate.parse("2022-09-26", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        revenueDetailsInDTO.setCountry(country);
        revenueDetailsInDTO.setBusinessName(businessName);
        revenueDetailsInDTO.setStartDate(startDate);
        revenueDetailsInDTO.setEndDate(endDate);

        PagedRevenueDetailsOutDTO pagedRevenueDetailsOutDTO = new PagedRevenueDetailsOutDTO();
        
        List<RevenueDetailsOutDTO> revenueDetailsOutDTOList = new ArrayList<>();
        RevenueDetailsOutDTO revenueDetailsOutDTO = new RevenueDetailsOutDTO();
        
        revenueDetailsOutDTO.setBusinessName(businessName);
        revenueDetailsOutDTO.setTotalRevenue(506.235);

        revenueDetailsOutDTO.setOffers(25.62156);
        revenueDetailsOutDTO.setInteraction(55.120);
        revenueDetailsOutDTO.setPromo(0.0);
        revenueDetailsOutDTO.setCatalog(0.0);
        revenueDetailsOutDTO.setCurrency("USD");
        revenueDetailsOutDTO.setCurrencySymbol("#");

        revenueDetailsOutDTOList.add(revenueDetailsOutDTO);

        pagedRevenueDetailsOutDTO.setRevenueDetails(revenueDetailsOutDTOList);
        pagedRevenueDetailsOutDTO.setLast(true);
        pagedRevenueDetailsOutDTO.setCount(5L);

        ResponseOutDTO responseOutDTO = new ResponseOutDTO(true, pagedRevenueDetailsOutDTO);
        String pgStart = "2";
        String pgSize = "5";
        
        int pageStart = Integer.parseInt(pgStart);
        pageStart = pageStart <= 0 ? 1 : pageStart;
        
        when(revenueService.revenueDetails(pageStart - 1, Integer.parseInt(pgSize), revenueDetailsInDTO))
                .thenReturn(pagedRevenueDetailsOutDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/revenue?")
                                        .queryParam("pgStart", pgStart)
                                        .queryParam("pgSize", pgSize)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
                                        .content(OBJECTMAPPER.writeValueAsBytes(revenueDetailsInDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(OBJECTMAPPER.writeValueAsString(responseOutDTO), mvcResult.getResponse().getContentAsString());

    }
}
