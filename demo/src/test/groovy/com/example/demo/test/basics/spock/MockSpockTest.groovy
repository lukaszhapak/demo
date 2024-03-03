package com.example.demo.test.basics.spock

import com.example.demo.test.basics.Customer
import com.example.demo.test.basics.CustomerRepository
import com.example.demo.test.basics.CustomerService
import com.example.demo.test.basics.NumberService
import spock.lang.Specification

class MockSpockTest extends Specification {
    NumberService numberService = Mock();

    CustomerRepository customerRepository = Mock();
    CustomerService CustomerService = new CustomerService(customerRepository);

    // mock allows stubbing method and verifying executions

    def "should verify call on mocked service"() {
        when:
        numberService.returningInt()

        then:
        1 * numberService.returningInt()
    }

    def "should verify call with argument on mocked service"() {
        when:
        numberService.returningInt(5)

        then:
        1 * numberService.returningInt(5)
    }

    def "should save customer"() {
        given:
        Customer customer = new Customer(1, "John", 24)

        when:
        CustomerService.save(customer)

        then:
        1 * customerRepository.save({ it.name == "John" && it.age == 24 })
    }
}
