package com.example.demo.test.unit.basics

import spock.lang.Specification

class ParameterizedSpockSpec extends Specification {

    static String jamesName = "James"

    def "single argument data table"() {
        expect:
        name.size() > 2

        where:
        name << ["John", "Jim", "Michael", jamesName]
    }

    def "data table example"() {
        expect:
        a + b == c

        where:
        a | b | c
        1 | 3 | 4
        3 | 4 | 7
        0 | 6 | 6
    }

    def "should contain #number in #list"() {
        expect:
        list.contains(number)

        where:
        number | list
        3      | [1, 3]
        4      | [4, 2, 1]
        6      | [6]
    }

    def "should get data from method"() {
        expect:
        name.size() > 2

        where:
        name << getNames()
    }

    List<String> getNames(){
        ["John", "Jim", "Michael", jamesName]
    }
}
