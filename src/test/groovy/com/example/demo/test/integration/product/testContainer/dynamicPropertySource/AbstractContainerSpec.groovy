package com.example.demo.test.integration.product.testContainer.dynamicPropertySource

import com.example.demo.test.integration.product.SampleProducts
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.spock.Testcontainers
import spock.lang.Specification

@Testcontainers
@SpringBootTest
abstract class AbstractContainerSpec extends Specification implements SampleProducts {

    public static PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:14.0")
//            .withDatabaseName("postgres")
//            .withUsername("postgres")
//            .withPassword("postgres")
            .withReuse(true)

    // from spring boot 3.1 it can be replaced with  @ServiceConnection
    // https://spring.io/blog/2023/06/23/improved-testcontainers-support-in-spring-boot-3-1
    @DynamicPropertySource
    static void getContainerConfig(DynamicPropertyRegistry registry) {
        postgres.start()
        println("Starting postgres database, connection details")
        println("Url = " + postgres.getJdbcUrl())
        println("username = " + postgres.getUsername())
        println("password = " + postgres.getPassword())
        registry.add("spring.datasource.url", postgres::getJdbcUrl)
        registry.add("spring.datasource.username", postgres::getUsername)
        registry.add("spring.datasource.password", postgres::getPassword)
    }
}
