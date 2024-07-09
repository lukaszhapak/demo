package com.example.demo.test.integration.product.testContainer.jdbc

import com.example.demo.test.integration.product.SampleProducts
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

// https://github.com/testcontainers/workshop/blob/main/step-4-your-first-testcontainers-integration.md
// https://java.testcontainers.org/modules/databases/jdbc/


@SpringBootTest(properties = "spring.datasource.url=jdbc:tc:postgresql:14.0://demo")
abstract class AbstractContainerSpec extends Specification implements SampleProducts {

}
