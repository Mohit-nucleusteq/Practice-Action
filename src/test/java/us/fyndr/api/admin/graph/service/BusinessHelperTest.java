package us.fyndr.api.admin.graph.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.Business;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dto.BusinessDTO;
import us.fyndr.api.admin.repository.BusinessRepository;

public class BusinessHelperTest {

    @InjectMocks
    private BusinessHelper businessHelper;

    @Mock
    private BusinessRepository businessRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGraphPayload() {
        Business business = new Business();

        Individual individual = new Individual();

        individual.setAccountStatus(AccountStatus.ACTIVE);
        individual.setAddressLine1("Address line 1");
        individual.setAddressLine2("address line 2");
        individual.setBusinessId(2L);
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

        Mockito.when(businessRepository.findById(individual.getBusinessId())).thenReturn(Optional.of(business));

        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setBizQRId(2L);
        businessDTO.setAccountStatus(AccountStatus.ACTIVE);
        businessDTO.setAddressLine1(individual.getAddressLine1());
        businessDTO.setAddressLine2(individual.getAddressLine2());
        businessDTO.setBusinessType(business.getBizType());
        businessDTO.setCity(individual.getCity());
        businessDTO.setCountry(individual.getCountry());
        businessDTO.setFyndrHandle(individual.getFyndrHandle());
        businessDTO.setPostalCode(individual.getPostalCode());
        businessDTO.setState(individual.getState());
        businessDTO.setBizQRId(business.getObjId());

        Map<String, BusinessDTO> businessMap = new HashMap<String, BusinessDTO>();

        businessMap.put("business", businessDTO);
        JSONObject expectedPayload = new JSONObject(businessMap);

        String payload = businessHelper.getGraphPayload(individual);

        assertEquals(expectedPayload.toString(), payload);

    }

}
