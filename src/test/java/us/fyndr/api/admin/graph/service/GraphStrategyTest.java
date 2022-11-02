package us.fyndr.api.admin.graph.service;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import us.fyndr.api.admin.dbo.Individual;

public class GraphStrategyTest {

    @Mock
    private BusinessHelper businessHelper;

    @Mock
    private CustomerHelper customerHelper;

    @InjectMocks
    private GraphStrategy graphStrategy;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetStrategyForBusiness() {
        Individual individual = new Individual();

        individual.setIsBusiness(true);

        GraphHelper businessHelper = graphStrategy.getStrategy(individual);

        assertTrue(businessHelper instanceof GraphHelper);
    }

    @Test
    public void testGetStrategyForIndividual() {
        Individual individual = new Individual();

        individual.setIsBusiness(false);

        GraphHelper customerHelper = graphStrategy.getStrategy(individual);

        assertTrue(customerHelper instanceof GraphHelper);
    }

}
