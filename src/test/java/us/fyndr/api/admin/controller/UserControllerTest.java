
/**
 * @author Prerna Goyal
 */
package us.fyndr.api.admin.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Instant;
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
import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.UserType;
import us.fyndr.api.admin.dto.AddressOutDTO;
import us.fyndr.api.admin.dto.IndividualOutDTO;
import us.fyndr.api.admin.dto.PagedIndividualOutDTO;
import us.fyndr.api.admin.dto.PhoneNumberOutDTO;
import us.fyndr.api.admin.dto.ResponseOutDTO;
import us.fyndr.api.admin.dto.SearchInDTO;
import us.fyndr.api.admin.dto.UpdateStatusInDTO;
import us.fyndr.api.admin.dto.UpdateStatusOutDTO;
import us.fyndr.api.admin.exception.UserNotFoundException;
import us.fyndr.api.admin.model.FetchUserTokenResponse;
import us.fyndr.api.admin.model.TokenUser;
import us.fyndr.api.admin.service.SearchService;
import us.fyndr.api.admin.service.TokenService;
import us.fyndr.api.admin.service.UserService;
import us.fyndr.api.admin.service.UserValidatorService;
import us.fyndr.api.admin.util.ErrorConstants;

public class UserControllerTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private SearchService searchService;

    @Mock
    private UserValidatorService userValidatorService;
    
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    private static final ObjectMapper OBJECTMAPPPER = new ObjectMapper();

    @BeforeEach
    public void setUp() {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void testMasqueradeAdminAsUser()
            throws JsonProcessingException, Exception {

        String authToken = "authToken";

        Long objId = 5L;

        TokenUser tokenUser = new TokenUser();
        tokenUser.setEmail("email");
        tokenUser.setObjId(1L);
        tokenUser.setBizName("bizName");
        tokenUser.setUserEntity("userEntity");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);

        FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();
        fetchUserTokenResponse.setAccessCode("accessCode");
        fetchUserTokenResponse.setAccessCodeExpiry("2000");
        fetchUserTokenResponse.setTokenUser(tokenUser);

        when(tokenService.masqueradeUser(objId, authToken))
                .thenReturn(fetchUserTokenResponse);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/admin/user/masquerade/{objId}", objId)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("authorization", authToken)).andReturn();

        assertEquals(HttpStatus.OK.value(),
                mvcResult.getResponse().getStatus());
        assertEquals(
                OBJECTMAPPPER.writeValueAsString(fetchUserTokenResponse),
                mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testIndividualMasqueradeAdminAsUser()
            throws JsonProcessingException, Exception {

        String authToken = "authToken";

        Long objId = 5L;

        TokenUser tokenUser = new TokenUser();
        tokenUser.setEmail("email");
        tokenUser.setObjId(1L);
        tokenUser.setBizName(null);
        tokenUser.setUserEntity("userEntity");
        tokenUser.setUserRole("userRole");
        tokenUser.setGeneratedBy(2);

        FetchUserTokenResponse fetchUserTokenResponse = new FetchUserTokenResponse();
        fetchUserTokenResponse.setAccessCode("accessCode");
        fetchUserTokenResponse.setAccessCodeExpiry("2000");
        fetchUserTokenResponse.setTokenUser(tokenUser);

        when(tokenService.masqueradeUser(objId, authToken))
                .thenReturn(fetchUserTokenResponse);

        doNothing().when(userValidatorService)
                .validateMasqueradeAdminAsUser(objId);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get("/admin/user/masquerade/{objId}", objId)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("authorization", authToken)).andReturn();

        assertEquals(HttpStatus.OK.value(),
                mvcResult.getResponse().getStatus());
        assertEquals(
                OBJECTMAPPPER.writeValueAsString(fetchUserTokenResponse),
                mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testValidateMasqueradeAdminAsUser()
            throws JsonProcessingException, Exception {

        String authToken = "authToken";

        Long objId = 5L;

        UserNotFoundException userNotFoundException = new UserNotFoundException
                (String.format(ErrorConstants.OBJECT_ID_NOT_FOUND, objId));

        doThrow(userNotFoundException).when(userValidatorService)
                .validateMasqueradeAdminAsUser(objId);

        mockMvc.perform(MockMvcRequestBuilders.get("/admin/user/masquerade/{objId}", objId)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .header("authorization", authToken))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result
                        .getResolvedException() instanceof UserNotFoundException))
                .andExpect(result -> assertEquals(String.format(ErrorConstants.OBJECT_ID_NOT_FOUND, objId), result.getResolvedException().getMessage()))
                .andReturn();

    }
    @Test
    public void testSearchActiveBusinessUsers() throws JsonProcessingException, Exception {

        SearchInDTO mockSearchInDTO = new SearchInDTO();
        List<AccountStatus> accountStatus = new ArrayList<>();
        accountStatus.add(AccountStatus.ACTIVE);
        List<UserType> userType = new ArrayList<>();
        userType.add(UserType.BUSINESS);
        mockSearchInDTO.setText("abc");
        mockSearchInDTO.setUserStatus(accountStatus);
        mockSearchInDTO.setUserType(userType);
        List<IndividualOutDTO> individualOutDTOList = new ArrayList<>();
        IndividualOutDTO individualOutDTO = new IndividualOutDTO();
        individualOutDTO.setIsBusiness(false);
        individualOutDTO.setBusinessName("xyz");
        individualOutDTO.setCreateDt(Instant.now());
        individualOutDTO.setName("Name");
        individualOutDTO.setWebsite("Web");
        individualOutDTO.setStatus(AccountStatus.ACTIVE);
        individualOutDTO.setEmail("xyz@gmail.com");
        PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
        phoneNumberOutDTO.setCountryCode("+91");
        phoneNumberOutDTO.setPhoneNumber("7509205576");
        individualOutDTO.setPhone(phoneNumberOutDTO);
        AddressOutDTO addressOutDTO = new AddressOutDTO();
        addressOutDTO.setAddressLine1("address1");
        addressOutDTO.setAddressLine2("address2");
        addressOutDTO.setCity("city");
        addressOutDTO.setCountry("country");
        addressOutDTO.setPostalCode("123");
        addressOutDTO.setState("state");
        individualOutDTO.setAddress(addressOutDTO);
        individualOutDTOList.add(individualOutDTO);
        PagedIndividualOutDTO mockPagedIndividualOutDTO = new PagedIndividualOutDTO();
        mockPagedIndividualOutDTO.setUsers(individualOutDTOList);
        mockPagedIndividualOutDTO.setLast(true);
        mockPagedIndividualOutDTO.setCount(5l);

        ResponseOutDTO responseOutDTO = new ResponseOutDTO(true, mockPagedIndividualOutDTO);
        String uri = "/admin/user/search?pgStart=0&pgSize=10";
        final String pgStart = "0";
        final String pgSize = "10";

        int pageStart = Integer.parseInt(pgStart);
        pageStart = pageStart <= 0 ? 1 : pageStart;

        when(searchService.searchIndividualUsers(pageStart - 1, Integer.parseInt(pgSize), mockSearchInDTO))
                .thenReturn(mockPagedIndividualOutDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mockSearchInDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        ObjectMapper objectMapper = new ObjectMapper();

        JavaTimeModule module = new JavaTimeModule();
        objectMapper.registerModule(module);

        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(objectMapper.writeValueAsString(responseOutDTO), mvcResult.getResponse().getContentAsString());

    }

    @Test
    public void testValidateSearchActiveBusinessUsers() throws JsonProcessingException, Exception {

        SearchInDTO mockSearchInDTO = new SearchInDTO();
        List<AccountStatus> accountStatus = new ArrayList<>();
        List<UserType> userType = new ArrayList<>();
        mockSearchInDTO.setText("");
        mockSearchInDTO.setUserStatus(accountStatus);
        mockSearchInDTO.setUserType(userType);

        String uri = "/admin/user/search?pgStart=0&pgSize=10";

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(mockSearchInDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(org.springframework.http.HttpStatus.OK.value(), mvcResult.getResponse().getStatus());

    }

    @Test
    public void testUpdateStatus() throws JsonProcessingException, Exception {

        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        
        UpdateStatusInDTO mockUpdateStatusInDTO = new UpdateStatusInDTO();

        mockUpdateStatusInDTO.setAccountStatus(AccountStatus.ACTIVE);

        UpdateStatusOutDTO mockUpdateStatusOutDTO = new UpdateStatusOutDTO();

        mockUpdateStatusOutDTO.setAccountStatus(AccountStatus.ACTIVE);
        mockUpdateStatusOutDTO.setMessage("Account status has been updated successfully");
        mockUpdateStatusOutDTO.setSuccess(true);

        String authToken = "authToken";
        Long objId = 7L;

        when(userService.updateStatus(mockUpdateStatusInDTO, objId, authToken)).thenReturn(mockUpdateStatusOutDTO);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/admin/user/status/{objId}", objId).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8").accept(MediaType.APPLICATION_JSON).header("authorization", authToken)
                .content(new ObjectMapper().writeValueAsString(mockUpdateStatusInDTO));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        
        assertEquals(
                OBJECTMAPPPER.writeValueAsString(mockUpdateStatusOutDTO),
                mvcResult.getResponse().getContentAsString());
    }

}