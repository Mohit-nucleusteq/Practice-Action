package us.fyndr.api.admin.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@DataJpaTest
@Sql(scripts = { "classpath:/ddl/schema-ddl.sql", "classpath:/dml/repository/individual_insertion.sql"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"classpath:/dml/repository/individual_deletion.sql"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class IndividualRepositoryTest{

    @Autowired
    private IndividualRepository individualRepository;

    //@Test
    void FetchObjIdByBusinessNameTest() {

        Long expectedObjId = 7L;
        Long actualObjId = individualRepository.fetchObjIdByBusinessName("bizName1");

        assertEquals(expectedObjId, actualObjId);
    }

}
