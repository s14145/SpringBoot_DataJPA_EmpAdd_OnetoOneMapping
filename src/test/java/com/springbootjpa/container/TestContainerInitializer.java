package com.springbootjpa.container;

import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.OracleContainer;
import org.testcontainers.utility.DockerImageName;

public class TestContainerInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final String IMAGE_NAME = "gvenzl/oracle-free:slim-faststart";

    private static final String CONTAINER_ORC_DATABASE = "TestDB";
    private static final String CONTAINER_ORC_USERNAME = "testuser";
    private static final String CONTAINER_ORC_PASSWORD = "testpwd";

    OracleContainer oracleContainer = new OracleContainer(
            DockerImageName.parse(IMAGE_NAME)
                    .asCompatibleSubstituteFor("gvenzl/oracle-free"))
            .withDatabaseName(CONTAINER_ORC_DATABASE)
            .withUsername(CONTAINER_ORC_USERNAME)
            .withPassword(CONTAINER_ORC_PASSWORD);

    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
    oracleContainer.start();
    TestPropertyValues.of(
            "spring.datasource.url=" + oracleContainer.getJdbcUrl(),
            "spring.datasource.databasename=" + oracleContainer.getDatabaseName(),
            "spring.datasource.username=" + oracleContainer.getUsername(),
            "spring.datasource.password="+ oracleContainer.getPassword()
    ).applyTo(applicationContext.getEnvironment());
    }
}