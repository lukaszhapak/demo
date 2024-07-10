package com.example.demo.test.unit.basics.prepareTestData

import com.example.demo.test.unit.basics.util.SampleCustomers
import spock.lang.Specification

class TraitSpockSpec extends Specification implements SampleCustomers {

    def "should get customer from trait and change his name to Michael"() {
        expect:
        john.getName() == "John"
        john.setName("Michael")
    }

    def "should get customer from trait and change his name to Jim"() {
        expect:
        john.getName() == "John"
        john.setName("Jim")
    }
}
