package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Instant;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.jdbc.Sql;

import us.fyndr.api.admin.dbo.AccountStatus;
import us.fyndr.api.admin.dbo.Individual;
import us.fyndr.api.admin.specification.StatisticsSpecification;

@DataJpaTest
@Sql(scripts = {"classpath:/ddl/schema-ddl.sql"})
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    private StatisticsSpecification statisticsSpecification = new StatisticsSpecification();

    @BeforeEach
    public void setup() {
        Individual individual = buildIndividual(1L, 123L, 10045L, "firstName", "lastName", "9999999999", "+91",
                "test@email.com", Instant.parse("2022-09-03T10:37:30.00Z"), "addressLine1", "addressLine2", "city",
                "state", "US", "111011", "fyndrHandle", "1902", "M", true, AccountStatus.ACTIVE);

        userRepository.save(individual);

        individual = buildIndividual(2L, 12L, null, "firstName1", "lastName1", "9999999991", "+91", "test1@email.com",
                Instant.parse("2022-09-03T10:37:30.00Z"), "addressLine1", "addressLine2", "city", "state", "US",
                "111011", "fyndrHandle1", "1902", "M", false, AccountStatus.ACTIVE);

        userRepository.save(individual);

        individual = buildIndividual(3L, 13L, null, "firstName2", "lastName2", "9999999992", "+91", "test2@email.com",
                Instant.parse("2022-09-03T10:37:30.00Z"), "addressLine1", "addressLine2", "city", "state", "US",
                "111011", "fyndrHandle2", "1902", "M", false, AccountStatus.INACTIVE);

        userRepository.save(individual);

        individual = buildIndividual(4L, 14L, 4L, "firstName4", "lastName4", "9999999994", "+91", "test4@email.com",
                Instant.parse("2022-09-03T10:37:30.00Z"), "addressLine1", "addressLine2", "city", "state", "US",
                "111011", "fyndrHandle4", "1902", "M", true, AccountStatus.ACTIVE);

        userRepository.save(individual);

        individual = buildIndividual(5L, 15L, 5L, "firstName5", "lastName5", "9999999995", "+91", "test5@email.com",
                Instant.parse("2022-09-03T10:37:30.00Z"), "addressLine1", "addressLine2", "city", "state", "US",
                "111011", "fyndrHandle5", "1902", "M", true, AccountStatus.ACTIVE);

        userRepository.save(individual);

        individual = buildIndividual(6L, 16L, null, "firstName6", "lastName6", "9999999996", "+91", "test6@email.com",
                Instant.parse("2022-09-03T10:37:30.00Z"), "addressLine1", "addressLine2", "city", "state", "US",
                "111011", "fyndrHandle6", "1902", "M", false, AccountStatus.SUSPENDED);

        userRepository.save(individual);

        individual = buildIndividual(7L, 17L, null, "firstName7", "lastName7", "9999999997", "+91", "test7@email.com",
                Instant.parse("2022-09-03T10:37:30.00Z"), "addressLine1", "addressLine2", "city", "state", "US",
                "111011", "fyndrHandle7", "1902", "M", false, AccountStatus.DELETED);

        userRepository.save(individual);

    }

    public void testActiveCountUsers() {

        Long users = userRepository.count(Specification.where(statisticsSpecification.findByActiveUsers()));

        assertEquals(4, users);

        Long merchant = userRepository.count(Specification
                .where(statisticsSpecification.findByActiveUsers().and(statisticsSpecification.findByMerchantUsers())));

        assertEquals(3, merchant);

        Long customers = users - merchant;
        assertEquals(1, customers);
    }

    private Individual buildIndividual(Long objid, Long qrId, Long businessId, String firstName, String lastName,
            String phone, String countryCode, String email, Instant createdDt, String addressLine1, String addressLine2,
            String city, String state, String country, String postalCode, String fyndrHandle, String yob, String gender,
            boolean isBusiness, AccountStatus accountStatus) {

        Individual individual = new Individual();
        individual.setObjId(objid);

        individual.setQrid(qrId);

        individual.setBusinessId(businessId);

        individual.setFirstName(firstName);

        individual.setLastName(lastName);

        individual.setPhone(phone);

        individual.setCountryCode(countryCode);

        individual.setEmail(email);

        individual.setCreatedDt(createdDt);

        individual.setAddressLine1(addressLine1);

        individual.setAddressLine2(addressLine2);

        individual.setCity(city);

        individual.setCountry(country);

        individual.setPostalCode(postalCode);

        individual.setFyndrHandle(fyndrHandle);

        individual.setYob(yob);

        individual.setGender(gender);

        individual.setState(state);

        individual.setIsBusiness(isBusiness);

        individual.setAccountStatus(accountStatus);

        return individual;
    }
}
