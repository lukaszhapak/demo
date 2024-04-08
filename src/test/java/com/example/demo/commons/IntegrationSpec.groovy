package com.example.demo.commons;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import spock.lang.Specification;

@SpringBootTest
@ActiveProfiles("test")
abstract class IntegrationSpec extends Specification {

}
