package com.example.demo.commons


import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
@ActiveProfiles(["test", "h2-p6spy"])
abstract class AbstractIntegrationSpec extends Specification {

}
