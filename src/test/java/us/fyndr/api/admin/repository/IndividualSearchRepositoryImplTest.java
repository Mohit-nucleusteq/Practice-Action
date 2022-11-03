package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.Individual;

@DataJpaTest
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql" })
@ComponentScan(basePackages = { "us.fyndr.api.admin.repository" })
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class IndividualSearchRepositoryImplTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IndividualSearchRepository individualSearchRepository;

    @BeforeEach
    public void setup() {
        Individual individual = buildIndividual(1L, "A", "B", "test1@gmail.com");
        userRepository.save(individual);

        individual = buildIndividual(2L, "A", "C", "test2@gmail.com");
        userRepository.save(individual);

        individual = buildIndividual(3L, "B", "A", "test3@gmail.com");
        userRepository.save(individual);

        individual = buildIndividual(4L, "B", "D", "test4@gmail.com");
        userRepository.save(individual);

        individual = buildIndividual(5L, "X", "Y", "test5@gmail.com");
        userRepository.save(individual);
    }

    public void testSearchByFiltersMethodWithPagination() {
        int pageNumber = 0;
        int maxResults = 3;

        List<Individual> expectedUsers = new ArrayList<>();
        Individual individual = new Individual();

        individual = buildIndividual(1L, "A", "B", "test1@gmail.com");
        expectedUsers.add(individual);

        individual = buildIndividual(2L, "A", "C", "test2@gmail.com");
        expectedUsers.add(individual);

        individual = buildIndividual(3L, "B", "A", "test3@gmail.com");
        expectedUsers.add(individual);

        String whereConditions = "";

        List<Individual> actualUsers = individualSearchRepository.searchByFiltersCriteria(whereConditions, pageNumber,
                maxResults);

        assertEquals(expectedUsers, actualUsers);
        assertEquals(maxResults, actualUsers.size());

        pageNumber = 1;
        expectedUsers = new ArrayList<>();
        individual = buildIndividual(4L, "B", "D", "test4@gmail.com");
        expectedUsers.add(individual);

        individual = buildIndividual(5L, "X", "Y", "test5@gmail.com");
        expectedUsers.add(individual);

        List<Individual> actualIndividualsForSecondPage = individualSearchRepository
                .searchByFiltersCriteria(whereConditions, pageNumber, maxResults);

        assertEquals(expectedUsers, actualIndividualsForSecondPage);
        assertNotEquals(maxResults, actualIndividualsForSecondPage.size());
        assertEquals(2, actualIndividualsForSecondPage.size());
    }

    public void testSearchByFiltersMethodWithoutPagination() {
        int pageNumber = 0;
        int maxResults = 10;

        List<Individual> expectedUsers = new ArrayList<>();

        Individual individual = buildIndividual(1L, "A", "B", "test1@gmail.com");
        expectedUsers.add(individual);

        individual = buildIndividual(2L, "A", "C", "test2@gmail.com");
        expectedUsers.add(individual);

        individual = buildIndividual(3L, "B", "A", "test3@gmail.com");
        expectedUsers.add(individual);

        individual = buildIndividual(4L, "B", "D", "test4@gmail.com");
        expectedUsers.add(individual);

        individual = buildIndividual(5L, "X", "Y", "test5@gmail.com");
        expectedUsers.add(individual);

        String whereConditions = "";

        List<Individual> actualIndividuals = individualSearchRepository.searchByFiltersCriteria(whereConditions,
                pageNumber, maxResults);

        assertEquals(expectedUsers, actualIndividuals);
        assertEquals(5, actualIndividuals.size());
        assertNotEquals(maxResults, actualIndividuals.size());
    }

    public void testCountSearchByFiltersCriteria() {

        String whereConditions = "";

        int actualCount = individualSearchRepository.countSearchByFiltersCriteria(whereConditions);

        assertEquals(5, actualCount);
    }

    private Individual buildIndividual(Long objid, String firstName, String lastName, String email) {

        Individual individual = new Individual();

        individual.setFirstName(firstName);

        individual.setLastName(lastName);

        individual.setEmail(email);

        individual.setObjId(objid);

        Long qrId = 100L;
        individual.setQrid(qrId);

        Long businessId = 123456L;
        individual.setBusinessId(businessId);

        String phone = "9999999999";
        individual.setPhone(phone);

        String countryCode = "+1";
        individual.setCountryCode(countryCode);

        Instant createdDt = Instant.parse("2022-09-03T10:37:30.00Z");
        individual.setCreatedDt(createdDt);

        String addressLine1 = "Address line 1";
        individual.setAddressLine1(addressLine1);

        String addressLine2 = "Address line 2";
        individual.setAddressLine2(addressLine2);

        String city = "City";
        individual.setCity(city);

        String country = "US";
        individual.setCountry(country);

        String postalCode = "34567";
        individual.setPostalCode(postalCode);

        String fyndrHandle = "fyndrHandle1";
        individual.setFyndrHandle(fyndrHandle);

        String yob = "1998";
        individual.setYob(yob);

        String gender = "M";
        individual.setGender(gender);

        String state = "State";
        individual.setState(state);

        boolean isBusiness = true;
        individual.setIsBusiness(isBusiness);

        AccountStatus accountStatus = AccountStatus.ACTIVE;
        individual.setAccountStatus(accountStatus);

        return individual;
    }
}
