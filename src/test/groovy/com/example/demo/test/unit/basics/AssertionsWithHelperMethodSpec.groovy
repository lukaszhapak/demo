package com.example.demo.test.unit.basics


import com.example.demo.test.unit.basics.customer.CustomerDTO
import spock.lang.Specification

class AssertionsWithHelperMethodSpec extends Specification {

    def "assert objects are equal with helper method"() {
        given:
        CustomerDTO customer1 = new CustomerDTO("John", 21)
        CustomerDTO customer2 = new CustomerDTO("John", 21)

        expect:
        compareCustomers(customer1, customer2)
    }

    void compareCustomers(CustomerDTO customerDTO1, CustomerDTO customerDTO2) {
        assert customerDTO1.getName() == customerDTO2.getName()
        assert customerDTO1.getAge() == customerDTO2.getAge()
    }
}
