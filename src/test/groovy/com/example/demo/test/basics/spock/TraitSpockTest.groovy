package com.example.demo.test.basics.spock


import spock.lang.Specification

class TraitSpockTest extends Specification implements SampleCustomers {

    def "should get customer from trait and change his name to Michael"() {
        expect:
        validCustomer.getName() == "John"
        validCustomer.setName("Michael")
    }

    def "should get customer from trait and change his name to Jim"() {
        expect:
        validCustomer.getName() == "John"
        validCustomer.setName("Jim")
    }
}
