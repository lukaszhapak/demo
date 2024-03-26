package com.example.demo.test.basics.spock

import spock.lang.Specification

class ParameterizedSpockTest extends Specification {

    static String jamesName = "James"

    def "data table example"() {
        expect:
        a + b == c

        where:
        a | b | c
        1 | 3 | 4
        3 | 4 | 7
        0 | 6 | 6
    }

    def "single argument data table"() {
        expect:
        name.size() > 2

        where:
        name << ["John", "Jim", "Michael", jamesName]
    }

    def "array in data table"() {
        expect:
        array.contains(number)

        where:
        number | array
        3      | [1, 3]
        4      | [4, 2, 1]
        6      | [6]
    }
}
