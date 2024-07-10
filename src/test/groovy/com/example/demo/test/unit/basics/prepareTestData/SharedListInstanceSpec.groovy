package com.example.demo.test.unit.basics.prepareTestData


import spock.lang.Specification

class SharedListInstanceSpec extends Specification {

    List<Integer> list = []

    def "should add number"() {
        when:
        list.add(6)

        then:
        list.size() == 1
    }

    def "should add two numbers"() {
        when:
        list.add(3)
        list.add(2)

        then:
        list.size() == 2
    }
}
