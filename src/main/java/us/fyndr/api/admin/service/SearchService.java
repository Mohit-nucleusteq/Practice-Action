package us.fyndr.api.admin.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.Business;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.dbo.UserType;
import us.fyndr.api.admin.dto.AddressOutDTO;
import us.fyndr.api.admin.dto.IndividualOutDTO;
import us.fyndr.api.admin.dto.PagedIndividualOutDTO;
import us.fyndr.api.admin.dto.PhoneNumberOutDTO;
import us.fyndr.api.admin.dto.SearchInDTO;
import us.fyndr.api.admin.repository.BusinessRepository;
import us.fyndr.api.admin.repository.IndividualSearchRepository;

/**
 * @author sakshi
 *
 */
@Service
public class SearchService {

    /**
     * . individualSearchRepository to search in individual table
     */
    @Autowired
    private IndividualSearchRepository individualSearchRepository;

    /**
     * . businessRepository to search in business table
     */
    @Autowired
    private BusinessRepository businessRepository;

    /**
     * @param pageNumber        is the current page Number.
     * @param maxResultsPerPage is the total results per page.
     * @param searchInDTO       has two search criteria - userType and userStatus
     * @return PagedIndividualOutDTO is a class represents the result in pagination.
     */
    public PagedIndividualOutDTO searchIndividualUsers(final int pageNumber, final int maxResultsPerPage,
            final SearchInDTO searchInDTO) {

        List<AccountStatus> accountStatusInDTOList = searchInDTO.getUserStatus();

        List<UserType> userTypeInDTOList = searchInDTO.getUserType();

        String text = searchInDTO.getText();

        String whereConditions = "";

        String country = searchInDTO.getCountry();

        if (Objects.nonNull(accountStatusInDTOList) && !accountStatusInDTOList.isEmpty()) {
            whereConditions = addUserStatusInSearchCriteria(accountStatusInDTOList, whereConditions);
        }

        if (Objects.nonNull(userTypeInDTOList) && !userTypeInDTOList.isEmpty()) {
            whereConditions = addUserTypeInSearchCriteria(userTypeInDTOList, whereConditions);
        }

        if (Objects.nonNull(text) && !text.isEmpty()) {
            whereConditions = addTextInSearchCriteria(text, whereConditions);
        }

        if (Objects.nonNull(country) && !country.isEmpty()) {
            whereConditions = addCountrySearchCriteria(country, whereConditions);
        }

        List<Individual> individualList = individualSearchRepository.searchByFiltersCriteria(whereConditions,
                pageNumber, maxResultsPerPage);

        PagedIndividualOutDTO pagedIndividualOutDTO = new PagedIndividualOutDTO();

        if (Objects.nonNull(individualList) && !individualList.isEmpty()) {
            List<IndividualOutDTO> individualOutDTOList = prepareIndividualOutDTO(individualList);
            pagedIndividualOutDTO.setUsers(individualOutDTOList);
        }

        int totalCountOfSearchQuery = individualSearchRepository.countSearchByFiltersCriteria(whereConditions);

        boolean isLast = false;
        if (totalCountOfSearchQuery <= (pageNumber + 1) * maxResultsPerPage) {
            isLast = true;
        }

        pagedIndividualOutDTO.setLast(isLast);
        pagedIndividualOutDTO.setCount((long) totalCountOfSearchQuery);
        return pagedIndividualOutDTO;
    }

    /**
     * @param individualList is a list of individualDBO
     * @return List<IndividualOutDTO> is a list of individualOutDTO
     */
    private List<IndividualOutDTO> prepareIndividualOutDTO(final List<Individual> individualList) {

        List<IndividualOutDTO> individualOutDTOList = new ArrayList<IndividualOutDTO>();

        for (Individual individual : individualList) {
            IndividualOutDTO individualOutDTO = new IndividualOutDTO();
            individualOutDTO.setObjId(individual.getObjId());
            individualOutDTO.setName(individual.getFirstName() + " " + individual.getLastName());

            AddressOutDTO addressOutDTO = new AddressOutDTO();
            addressOutDTO.setAddressLine1(individual.getAddressLine1());
            addressOutDTO.setAddressLine2(individual.getAddressLine2());
            addressOutDTO.setCity(individual.getCity());
            addressOutDTO.setCountry(individual.getCountry());
            addressOutDTO.setPostalCode(individual.getPostalCode());
            addressOutDTO.setState(individual.getState());
            individualOutDTO.setAddress(addressOutDTO);
            individualOutDTO.setIsBusiness(individual.isBusiness());
            individualOutDTO.setEmail(individual.getEmail());

            PhoneNumberOutDTO phoneNumberOutDTO = new PhoneNumberOutDTO();
            phoneNumberOutDTO.setCountryCode(individual.getCountryCode());
            phoneNumberOutDTO.setPhoneNumber(individual.getPhone());
            individualOutDTO.setPhone(phoneNumberOutDTO);

            if (individual.isBusiness()) {
                Business business = businessRepository.findById(individual.getBusinessId()).get();
                individualOutDTO.setBusinessName(business.getBusinessName());
                individualOutDTO.setWebsite(business.getWebsite());
            }
            individualOutDTO.setCreateDt(individual.getCreatedDt());
            individualOutDTO.setStatus(individual.getAccountStatus());
            individualOutDTOList.add(individualOutDTO);
        }
        return individualOutDTOList;
    }

    /**
     * @param accountStatusInDTOList is one of the search criteria based on which
     *                               query will return the users. Accepted Values:
     *                               ACTIVE, INACTIVE, SUSPENDED, DELETED
     * @param whereCondition         whereCondition variable has the where clauses
     * @return the where condition string with appended accountStatus search
     *         criteria in where clause.
     */
    private String addUserStatusInSearchCriteria(final List<AccountStatus> accountStatusInDTOList,
            final String whereCondition) {

        String[] accountStatusArray = Arrays.toString(accountStatusInDTOList.toArray()).replace("[", "")
                .replace("]", "").split(",");
        List<String> accountStatusList = Arrays.asList(accountStatusArray);

        String whereConditionForAccountStatus = whereCondition + " AND im.account_status IN (\'"
                + String.join("\',\'", accountStatusList).replace(" ", "") + "\')";

        return whereConditionForAccountStatus;
    }

    /**
     * @param userTypeInDTOList is one of the search criteria based on which query
     *                          will return the users. Accepted Value: BUSINESS,
     *                          INDIVIDUAL.
     * @param whereCondition    variable has the where clauses.
     * @return the where condition string with appended userType search criteria in
     *         where clause.
     */
    private String addUserTypeInSearchCriteria(final List<UserType> userTypeInDTOList, final String whereCondition) {

        List<String> isBusinessList = new ArrayList<>();

        for (UserType userTypeInDTO : userTypeInDTOList) {
            if (UserType.BUSINESS.name().equals(userTypeInDTO.name())) {
                isBusinessList.add("true");
            } else {
                isBusinessList.add("false");
            }
        }

        String[] userTypeArray = Arrays.toString(isBusinessList.toArray()).replace("[", "").replace("]", "").split(",");
        List<String> isBusinessListNames = Arrays.asList(userTypeArray);

        String whereConditionForUserType = whereCondition + " AND im.is_business in (\'"
                + String.join("\',\'", isBusinessListNames).replace(" ", "") + "\')";

        return whereConditionForUserType;
    }

    /**
     * @param text           is a one of the search criteria based on which query
     *                       will return the users.
     * @param whereCondition variable has the where clauses.
     * @return the where condition with appended text search criteria in where
     *         clause.
     */
    private String addTextInSearchCriteria(final String text, final String whereCondition) {

        String whereConditionForText = whereCondition + " AND (( im.first_name ILIKE '%" + text
                + "%' ) OR ( im.last_name ILIKE '%" + text + "%' ) OR ( bm.biz_name ILIKE '%" + text + "%' ))";

        return whereConditionForText;
    }

    private String addCountrySearchCriteria(final String country, final String whereCondition) {

        String whereConditionForCountry = whereCondition + " AND ( im.country = '" + country + "' )";
        return whereConditionForCountry;
    }
}
