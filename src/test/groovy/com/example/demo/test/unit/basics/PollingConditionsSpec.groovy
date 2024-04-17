package com.example.demo.test.unit.basics

import com.example.demo.test.unit.basics.customer.Customer
import com.example.demo.test.unit.basics.customer.CustomerService
import com.example.demo.test.unit.basics.util.TestCustomerRepository
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

class PollingConditionsSpec extends Specification {

    CustomerService customerService = new CustomerService(new TestCustomerRepository())

    def "should save customer"() {
        given:
        PollingConditions conditions = new PollingConditions(timeout: 0.2)
        Customer customer = new Customer("John", 24)

        when:
        customerService.saveInNewThread(customer)

        then:
        conditions.eventually {
            customerService.getCustomerByName("John2") != null
        }
    }
}
