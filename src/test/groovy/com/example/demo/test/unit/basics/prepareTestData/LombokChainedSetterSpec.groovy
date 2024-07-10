package com.example.demo.test.unit.basics.prepareTestData


import com.example.demo.test.unit.basics.customer.CustomerValidator
import com.example.demo.test.unit.basics.util.SampleCustomers
import spock.lang.Specification

class LombokChainedSetterSpec extends Specification implements SampleCustomers {

    CustomerValidator customerValidator = new CustomerValidator()

    def "should not save invalid customer"() {
        expect:
        !customerValidator.validate(john.setName("1"))
    }
}
