package us.fyndr.api.admin.repository;

import java.util.List;

import us.fyndr.api.admin.dbo.Individual;

/**
 * @author saksh IndividualSearchRepository class contains custom functions.
 */
public interface IndividualSearchRepository {

    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to search users.
     * @param pageNumber is the current page Number.
     * @param maxResults is the total results per page.
     * @return List<Individual> object is a pagination object. This method
     *         filters all the users based on the search criteria: text,
     *         accountStatus, userType
     */
    List<Individual> searchByFiltersCriteria(String whereConditions, int pageNumber, int maxResults);

    /**
     * @param whereConditions specifies the filter criteria based on which an admin
     *                        wants to search users.
     * @return int. This method returns the count for the above search query.
     */
    int countSearchByFiltersCriteria(String whereConditions);

}
