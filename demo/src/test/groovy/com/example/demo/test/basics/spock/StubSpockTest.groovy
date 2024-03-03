package com.example.demo.test.basics.spock

import com.example.demo.test.basics.Customer
import com.example.demo.test.basics.CustomerRepository
import com.example.demo.test.basics.CustomerService
import com.example.demo.test.basics.NumberService
import spock.lang.Specification

class StubSpockTest extends Specification {
    NumberService numberService = Stub();

    CustomerRepository customerRepository = Stub();
    CustomerService customerService = new CustomerService(customerRepository);

    def "should get number from stubbed service"() {
        given:
        numberService.returningInt() >> 3

        when:
        int number = numberService.returningInt()

        then:
        number == 3
    }

    def "should get customer from stubbed repository"() {
        given:
        customerRepository.getCustomerById(12) >> new Customer(12, "John", 23)

        when:
        Customer customer = customerService.getCustomerById(12)

        then:
        customer.name == "John"
        customer.age == 23
    }
}
