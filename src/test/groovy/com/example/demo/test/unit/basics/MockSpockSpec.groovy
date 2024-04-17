package com.example.demo.test.unit.basics

import com.example.demo.test.unit.basics.customer.Customer
import com.example.demo.test.unit.basics.customer.CustomerRepository
import com.example.demo.test.unit.basics.customer.CustomerService
import com.example.demo.test.unit.basics.number.NumberService
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class MockSpockSpec extends Specification {

    NumberService numberService = Mock()

    CustomerRepository customerRepository = Mock()
    CustomerService customerService = new CustomerService(customerRepository)

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

    def "should save customer and verify its fields"() {
        given:
        Customer customer = new Customer(1, "John", 24)

        when:
        customerService.save(customer)

        then:
        1 * customerRepository.save({ it.name == "John" && it.age == 24 })
    }

    def "should save customer and capture object"() {
        given:
        Customer savedCustomer
        Customer customer = new Customer(1, "John", 24)

        when:
        customerService.save(customer)

        then:
        1 * customerRepository.save(_) >> {arguments -> savedCustomer = arguments[0] }
        assertThat(customer).usingRecursiveComparison().isEqualTo(savedCustomer)
    }
}
