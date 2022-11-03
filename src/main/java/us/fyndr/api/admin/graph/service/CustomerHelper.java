package us.fyndr.api.admin.graph.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.fyndr.api.admin.dbo.CodeMaster;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dto.IndividualDTO;
import us.fyndr.api.admin.repository.CodeMasterRepository;

/**
 * @author saksh CustomerHelper class helps to generate the request payload of a
 *         user for a graph API.
 */
@Component
public class CustomerHelper implements GraphHelper {

    /**
     * The CodeMasterRepository object.
     */
    @Autowired
    private CodeMasterRepository codeMasterRepository;

    /**
     * @param individual stores the details of a user.
     * @return String which will work as a request payload in graph API.
     *         getGraphPayload function generates the payload of a
     *         customer/individual for a graph API
     */
    @Override
    public String getGraphPayload(final Individual individual) {

        CodeMaster codeMaster = codeMasterRepository.findById(individual.getQrid()).get();

        IndividualDTO individualDTO = new IndividualDTO();
        individualDTO.setCodeMasterQrId(codeMaster.getObjId());
        individualDTO.setYob(individual.getYob());
        individualDTO.setGender(individual.getGender());
        individualDTO.setCountry(individual.getCountry());
        individualDTO.setCity(individual.getCity());
        individualDTO.setFyndrHandle(individual.getFyndrHandle());
        individualDTO.setAccountStatus(individual.getAccountStatus());

        Map<String, IndividualDTO> individualMap = new HashMap<String, IndividualDTO>();
        individualMap.put("individual", individualDTO);

        JSONObject payload = new JSONObject(individualMap);
        return payload.toString();
    }
}
