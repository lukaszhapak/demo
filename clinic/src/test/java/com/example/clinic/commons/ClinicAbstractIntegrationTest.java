package com.example.clinic.commons;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import com.example.clinic.ClinicApplication;
import com.example.commons.test.AbstractTestContainerIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@Sql(value = "classpath:data.sql", executionPhase = BEFORE_TEST_METHOD)
@Sql(value = "classpath:clean-data.sql", executionPhase = AFTER_TEST_METHOD)
@SpringBootTest(classes = {ClinicApplication.class}, webEnvironment = RANDOM_PORT)
public abstract class ClinicAbstractIntegrationTest extends AbstractTestContainerIntegrationTest {

}
