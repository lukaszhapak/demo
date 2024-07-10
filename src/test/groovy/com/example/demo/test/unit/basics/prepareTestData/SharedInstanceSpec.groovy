package com.example.demo.test.unit.basics.prepareTestData

import com.example.demo.test.unit.basics.customer.CustomerDTO
import spock.lang.Specification

class SharedInstanceSpec extends Specification {

    CustomerDTO john = new CustomerDTO("John", 22)

    def "should get customer from trait and change his name to Michael"() {
        expect:
        john.getName() == "John"
        john.setName("Michael")
    }

    def "should get customer from trait and change his name to Jim"() {
        expect:
        john.getName() == "John"
        john.setName("Jim")
    }
}
