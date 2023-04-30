package com.example.clinic.commons;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import com.example.clinic.ClinicApplication;
import com.example.commons.test.AbstractTestContainerIntegrationTest;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ClinicApplication.class, ClinicTestConfig.class}, webEnvironment = RANDOM_PORT)
public abstract class ClinicAbstractIntegrationTest extends AbstractTestContainerIntegrationTest {

}
