// https://spockframework.org/spock/docs
package com.example.demo.test.basics.spock

import com.example.demo.test.basics.Customer
import com.example.demo.test.basics.CustomerDTO
import com.example.demo.test.basics.NumberService
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class AssertionsSpockTest extends Specification {
    NumberService numberService = new NumberService();

    def "should get number from service"() {
        expect:
        numberService.returningInt() == 5
    }

    def "should compare customerDTO and customer"() {
        given:
        CustomerDTO CustomerDTO = new CustomerDTO("John", 24)
        Customer customer = new Customer(1, "John", 24)

        expect:
        // not found recursive comparison in spock so using assertJ here
        assertThat(CustomerDTO).usingRecursiveComparison().isEqualTo(customer)
    }
}
