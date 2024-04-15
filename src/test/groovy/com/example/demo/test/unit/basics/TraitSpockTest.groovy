package com.example.demo.test.unit.basics

import com.example.demo.test.unit.SampleCustomers
import spock.lang.Specification

class TraitSpockTest extends Specification implements SampleCustomers {

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
