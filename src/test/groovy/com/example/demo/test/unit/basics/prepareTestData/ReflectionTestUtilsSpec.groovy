package com.example.demo.test.unit.basics.prepareTestData


import com.example.demo.test.unit.basics.customer.CustomerDTO
import com.example.demo.test.unit.basics.customer.CustomerValidator
import com.example.demo.test.unit.basics.util.SampleCustomers
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Specification

class ReflectionTestUtilsSpec extends Specification implements SampleCustomers {

    CustomerValidator customerValidator = new CustomerValidator()

    def "should not save invalid customer"() {
        given:
        CustomerDTO customer = getCustomer(Map.of("name", "1"))

        expect:
        !customerValidator.validate(customer)
    }

    static CustomerDTO getCustomer(Map<String, Object> fieldsValues) {
        CustomerDTO customer = new CustomerDTO("John", 22)
        fieldsValues.forEach((key, value) -> ReflectionTestUtils.setField(customer, key, value))
        return customer
    }
}