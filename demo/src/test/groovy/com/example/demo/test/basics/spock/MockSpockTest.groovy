package com.example.demo.test.basics.spock

import com.example.demo.test.basics.Customer
import com.example.demo.test.basics.CustomerRepository
import com.example.demo.test.basics.CustomerService
import com.example.demo.test.basics.NumberService
import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class MockSpockTest extends Specification {
    NumberService numberService = Mock();

    CustomerRepository customerRepository = Mock();
    CustomerService CustomerService = new CustomerService(customerRepository);

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
        CustomerService.save(customer)

        then:
        1 * customerRepository.save({ it.name == "John" && it.age == 24 })
    }

    def "should save customer and capture object"() {
        given:
        Customer savedCustomer
        Customer customer = new Customer(1, "John", 24)

        when:
        CustomerService.save(customer)

        then:
        1 * customerRepository.save(_) >> {arguments -> savedCustomer = arguments[0] }
        assertThat(customer).usingRecursiveComparison().isEqualTo(savedCustomer)
    }
}
