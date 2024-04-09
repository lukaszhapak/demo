package com.example.demo.test.unit.spock

import com.example.demo.test.unit.Customer
import com.example.demo.test.unit.CustomerDTO
import com.example.demo.test.unit.NumberService
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class AssertionsSpockTest extends Specification {
    NumberService numberService = new NumberService()

    def "should get number from service"() {
        expect:
        numberService.returningInt() == 5
    }

    def "should compare customerDTO and customer"() {
        given:
        CustomerDTO customerDTO = new CustomerDTO("John", 24)
        Customer customer = new Customer(1, "John", 24)

        expect:
        // not found recursive comparison in spock so using assertJ here
        assertThat(customerDTO).usingRecursiveComparison().isEqualTo(customer)
    }

    def "verify all example"() {
        expect:
        verifyAll {
            numberService.returningInt() == 5
            numberService.returningInt(23) == 23
        }
    }

    def "multiple when then blocks"() {
        given:
        CustomerDTO customerDTO = new CustomerDTO("John", 24)

        when:
        customerDTO.setName("123")

        then:
        customerDTO.getName() == "123"

        when:
        customerDTO.setName("321")

        then:
        customerDTO.getName() == "321"
    }
}
