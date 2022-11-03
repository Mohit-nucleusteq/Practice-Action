package us.fyndr.api.admin.graph.service;

import us.fyndr.api.admin.dbo.Individual;

/**
 * @author saksh GraphHelper interface has getGraphPayload method which will
 *         help to generate payload for graph API. BusinessHelper and
 *         CustomerHelper Class implements this interface.
 */
public interface GraphHelper {

    /**
     * @param object stores information of a user.
     * @return String which will work as a request payload in graph API.
     * getGraphPayload function generates the payload of a business/individual user for a graph API
     */
    String getGraphPayload(Individual object);
}
