package us.fyndr.api.admin.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.doNothing;

import java.time.Instant;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.Business;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dto.UpdateStatusInDTO;
import us.fyndr.api.admin.dto.UpdateStatusOutDTO;
import us.fyndr.api.admin.exception.UserBadRequestException;
import us.fyndr.api.admin.exception.UserNotFoundException;
import us.fyndr.api.admin.graph.service.GraphHelper;
import us.fyndr.api.admin.graph.service.GraphStrategy;
import us.fyndr.api.admin.repository.BusinessRepository;
import us.fyndr.api.admin.repository.UserRepository;

public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private BusinessRepository businessRepository;

    @Mock
    private SNSService snsService;

    @Mock
    private GraphStrategy graphStrategy;

    @Mock
    private GraphHelper graphHelper;

    @Mock
    private GraphClientService graphClientService;

    private String authToken = null;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testStatusChangeForBusiness() throws UserNotFoundException, UserBadRequestException {

        Long objId = 7L;
        UpdateStatusInDTO updateStatusInDTO = new UpdateStatusInDTO();
        updateStatusInDTO.setAccountStatus(AccountStatus.DELETED);

        UpdateStatusOutDTO expectedUpdateStatusOutDTO = new UpdateStatusOutDTO();

        Individual individual = new Individual();

        individual.setAccountStatus(AccountStatus.ACTIVE);
        individual.setAddressLine1("Address line 1");
        individual.setAddressLine2("address line 2");
        individual.setBusinessId(2l);
        individual.setCity("city");
        individual.setCountry("country");
        individual.setCountryCode("country code");
        individual.setCreatedDt(Instant.now());
        individual.setEmail("Test@gmail.com");
        individual.setFirstName("first name");
        individual.setFyndrHandle("fyndr Handle");
        individual.setGender("gender");
        individual.setIsBusiness(true);
        individual.setLastName("last name");
        individual.setObjId(7L);
        individual.setPhone("phone number");
        individual.setPostalCode("postal code");
        individual.setQrid(1l);
        individual.setState("state");
        individual.setYob("yob");

        Business business = new Business();

        business.setObjId(122l);
        business.setBusinessName("business name");
        business.setBizType("business type");
        business.setWebsite("website");

        expectedUpdateStatusOutDTO.setAccountStatus(AccountStatus.DELETED);
        expectedUpdateStatusOutDTO.setMessage("Account status has been updated successfully");
        expectedUpdateStatusOutDTO.setSuccess(true);

        Mockito.when(userRepository.findById(objId)).thenReturn(Optional.of(individual));
        Mockito.when(businessRepository.findById(individual.getBusinessId())).thenReturn(Optional.of(business));

        Mockito.when(graphStrategy.getStrategy(individual)).thenReturn(graphHelper);
        Mockito.when(graphHelper.getGraphPayload(individual)).thenReturn("payload");

        UpdateStatusOutDTO updateStatusOutDTO1 = userService.updateStatus(updateStatusInDTO, objId, authToken);

        assertEquals(expectedUpdateStatusOutDTO.getAccountStatus(), updateStatusOutDTO1.getAccountStatus());
        assertEquals(expectedUpdateStatusOutDTO.getMessage(), updateStatusOutDTO1.getMessage());
        assertEquals(expectedUpdateStatusOutDTO.getSuccess(), updateStatusOutDTO1.getSuccess());
    }

    @Test
    public void testStatusSame() throws UserNotFoundException, UserBadRequestException {
        Long objId = 1L;

        UpdateStatusInDTO updateStatusInDTO = new UpdateStatusInDTO();
        updateStatusInDTO.setAccountStatus(AccountStatus.ACTIVE);

        UpdateStatusOutDTO expectedUpdateStatusOutDTO = new UpdateStatusOutDTO();

        Individual individual = new Individual();

        individual.setAccountStatus(AccountStatus.ACTIVE);

        expectedUpdateStatusOutDTO.setAccountStatus(AccountStatus.ACTIVE);

        Mockito.when(userRepository.findById(objId)).thenReturn(Optional.of(individual));

        UpdateStatusOutDTO updateStatusOutDTO1 = userService.updateStatus(updateStatusInDTO, objId, authToken);

        assertEquals(expectedUpdateStatusOutDTO.getAccountStatus(), updateStatusOutDTO1.getAccountStatus());
    }

    @Test
    public void testStatusChangeForCustomer() throws UserNotFoundException, UserBadRequestException {
        Long objId = 1L;

        UpdateStatusInDTO updateStatusInDTO = new UpdateStatusInDTO();
        updateStatusInDTO.setAccountStatus(AccountStatus.DELETED);

        UpdateStatusOutDTO expectedUpdateStatusOutDTO = new UpdateStatusOutDTO();

        Individual individual = new Individual();
        individual.setAccountStatus(AccountStatus.ACTIVE);
        individual.setAddressLine1("Address line 1");
        individual.setAddressLine2("address line 2");
        individual.setBusinessId(2l);
        individual.setCity("city");
        individual.setCountry("country");
        individual.setCountryCode("country code");
        individual.setCreatedDt(Instant.now());
        individual.setEmail("Test@gmail.com");
        individual.setFirstName("first name");
        individual.setFyndrHandle("fyndr Handle");
        individual.setGender("gender");
        individual.setIsBusiness(false);
        individual.setLastName("last name");
        individual.setObjId(7L);
        individual.setPhone("phone number");
        individual.setPostalCode("postal code");
        individual.setQrid(1l);
        individual.setState("state");
        individual.setYob("yob");

        expectedUpdateStatusOutDTO.setAccountStatus(AccountStatus.DELETED);
        expectedUpdateStatusOutDTO.setMessage("Account status has been updated successfully");
        expectedUpdateStatusOutDTO.setSuccess(true);

        Mockito.when(userRepository.findById(objId)).thenReturn(Optional.of(individual));

        Mockito.when(graphStrategy.getStrategy(individual)).thenReturn(graphHelper);
        Mockito.when(graphHelper.getGraphPayload(individual)).thenReturn("payload");

        individual.setAccountStatus(AccountStatus.ACTIVE);

        expectedUpdateStatusOutDTO.setAccountStatus(AccountStatus.DELETED);

        UpdateStatusOutDTO updateStatusOutDTO1 = userService.updateStatus(updateStatusInDTO, objId, authToken);

        assertEquals(expectedUpdateStatusOutDTO.getAccountStatus(), updateStatusOutDTO1.getAccountStatus());

    }

    @Test
    public void statusForIndividualNotInactive() throws UserNotFoundException, UserBadRequestException {
        Long objId = 1L;

        UpdateStatusInDTO updateStatusInDTO = new UpdateStatusInDTO();
        updateStatusInDTO.setAccountStatus(AccountStatus.INACTIVE);

        UpdateStatusOutDTO expectedUpdateStatusOutDTO = new UpdateStatusOutDTO();

        Individual individual = new Individual();
        individual.setIsBusiness(false);

        individual.setAccountStatus(AccountStatus.ACTIVE);

        expectedUpdateStatusOutDTO.setAccountStatus(AccountStatus.INACTIVE);

        Mockito.when(userRepository.findById(objId)).thenReturn(Optional.of(individual));

        UserBadRequestException exception = assertThrows(UserBadRequestException.class, () -> {
            userService.updateStatus(updateStatusInDTO, objId, authToken);
        });

        assertEquals(exception.getMessage(), "Individual users can not have inactive account status");
    }

    @Test
    public void statusForNullValues() throws UserNotFoundException, UserBadRequestException {
        Long objId = 3l;

        UpdateStatusInDTO updateStatusInDTO = new UpdateStatusInDTO();
        updateStatusInDTO.setAccountStatus(AccountStatus.INACTIVE);

        try {
            userService.updateStatus(updateStatusInDTO, objId, authToken);
            fail("Expected exception was not thrown");

        } catch (UserNotFoundException e) {
            assertEquals("User is not registered with id 3", e.getMessage());
        }

    }

}
