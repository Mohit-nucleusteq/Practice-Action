package us.fyndr.api.admin.graph.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.fyndr.api.admin.dbo.Individual;

/**
 * @author saksh
 *GraphStrategy class decides the strategy based on the type of a user.
 */
@Component
public class GraphStrategy {

    /**
     * The BusinessHelper object.
     */
    @Autowired
    private BusinessHelper businessHelper;

    /**
     * The CustomerHelper object.
     */
    @Autowired
    private CustomerHelper customerHelper;

    /**
     * @param individual helps to decide the strategy
     * @return GraphHelper - businessHelper or customerHelper
     */
    public GraphHelper getStrategy(final Individual individual) {
        return individual.isBusiness() ? businessHelper : customerHelper;
     }
}
