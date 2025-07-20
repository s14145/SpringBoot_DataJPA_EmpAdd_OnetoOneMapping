package com.springbootjpa.repository;

import com.springbootjpa.container.ContainerTest;
import com.springbootjpa.entity.Privilege;
import com.springbootjpa.entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@Testcontainers
class UserRepositoryTest {

    /**
     private static final String IMAGE_NAME = "gvenzl/oracle-xe:21-slim-faststart";
     private static final String CONTAINER_ORC_DATABASE = "TestDB";
     private static final String CONTAINER_ORC_USERNAME = "testuser";
     private static final String CONTAINER_ORC_PASSWORD = "testpwd";

    @Container
    static OracleContainer oracleContainer = new OracleContainer(IMAGE_NAME)
            //.withDatabaseName(CONTAINER_ORC_DATABASE)
            //.withStartupTimeout(Duration.ofMinutes(3))
            .withUsername(CONTAINER_ORC_USERNAME)
            .withPassword(CONTAINER_ORC_PASSWORD);


    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", oracleContainer::getJdbcUrl);
    propertyRegistry.add("spring.datasource.databasename", oracleContainer::getDatabasename;
        propertyRegistry.add("spring.datasource.username", oracleContainer::getUsername);
        propertyRegistry.add("spring.datasource.password", oracleContainer::getPassword);
    }
     */

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    @Test
    void findByUsernameTestSuccess() {

        Privilege privilege1 = new Privilege("Admin");
        Privilege privilege2 = new Privilege("Supervisor");

        Set<Privilege> privileges = new HashSet<>();
        privileges.add(privilege1);
        privileges.add(privilege2);

        Users user1 = new Users("Hari", "Pandey", privileges);

        //privilegeRepository.saveAll(privileges);
        //userRepository.save(user1);
        testEntityManager.merge(privilege1);
        testEntityManager.merge(privilege2);
        testEntityManager.merge(user1);
        testEntityManager.flush();

        Users actualUser = userRepository.findByUsername("Hari").get();

        assertNotNull(actualUser);
        assertEquals("Hari", actualUser.getUsername());
    }
}