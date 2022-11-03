package us.fyndr.api.admin.graph.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.fyndr.api.admin.dbo.Business;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dto.BusinessDTO;
import us.fyndr.api.admin.repository.BusinessRepository;

/**
 * @author saksh
 *BusinessHelper class helps to generate the request payload of a business user for a graph API.
 */
@Component
public class BusinessHelper implements GraphHelper {

    /**
     * The BusinessRepository object.
     */
    @Autowired
    private BusinessRepository businessRepository;

    /**
     * @param individual stores the details of a user.
     * @return String which will work as a request payload in graph API.
     * getGraphPayload function generates the payload of a business user for a graph API
     */
    @Override
    public String getGraphPayload(final Individual individual) {

        Business business = businessRepository.findById(individual.getBusinessId()).get();

        BusinessDTO businessDTO = new BusinessDTO();
        businessDTO.setBizQRId(business.getObjId());
        businessDTO.setAddressLine1(individual.getAddressLine1());
        businessDTO.setAddressLine2(individual.getAddressLine2());
        businessDTO.setBusinessType(business.getBizType());
        businessDTO.setFyndrHandle(individual.getFyndrHandle());
        businessDTO.setCity(individual.getCity());
        businessDTO.setCountry(individual.getCountry());
        businessDTO.setState(individual.getState());
        businessDTO.setPostalCode(individual.getPostalCode());
        businessDTO.setAccountStatus(individual.getAccountStatus());

        Map<String, BusinessDTO> businessMap = new HashMap<String, BusinessDTO>();

        businessMap.put("business", businessDTO);

        JSONObject payload = new JSONObject(businessMap);
        return payload.toString();
    }
}
