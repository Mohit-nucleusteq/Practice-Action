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
import us.fyndr.api.admin.dbo.CodeMaster;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dto.IndividualDTO;
import us.fyndr.api.admin.repository.CodeMasterRepository;

public class CustomerHelperTest {

    @InjectMocks
    private CustomerHelper customerHelper;

    @Mock
    private CodeMasterRepository codeMasterRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetGraphPayload() {
        CodeMaster codeMaster = new CodeMaster();

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

        Mockito.when(codeMasterRepository.findById(individual.getQrid())).thenReturn(Optional.of(codeMaster));

        IndividualDTO individualDTO = new IndividualDTO();
        individualDTO.setCodeMasterQrId(codeMaster.getObjId());
        individualDTO.setYob(individual.getYob());
        individualDTO.setGender(individual.getGender());
        individualDTO.setCountry(individual.getCountry());
        individualDTO.setCity(individual.getCity());
        individualDTO.setFyndrHandle(individual.getFyndrHandle());
        individualDTO.setAccountStatus(AccountStatus.ACTIVE);

        Map<String, IndividualDTO> individualMap = new HashMap<String, IndividualDTO>();
        individualMap.put("individual", individualDTO);

        JSONObject expectedPayload = new JSONObject(individualMap);

        String payload = customerHelper.getGraphPayload(individual);

        assertEquals(expectedPayload.toString(), payload);
    }
}
