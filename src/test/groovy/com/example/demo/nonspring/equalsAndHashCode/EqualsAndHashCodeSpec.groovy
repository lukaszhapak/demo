package com.example.demo.nonspring.equalsAndHashCode

import spock.lang.Specification

class EqualsAndHashCodeSpec extends Specification {

    NameWithDefaultEquals johnWithDefaultEquals = new NameWithDefaultEquals("John")

    Name john = new Name("John")
    Name jim = new Name("Jim")

    NameAndAge john25 = new NameAndAge("John", 25)

    def "test equals nad hashCode"() {
        expect:
        "Ea".hashCode() == "FB".hashCode()
        "Ea" != "FB"

        johnWithDefaultEquals != new NameWithDefaultEquals("John")
        johnWithDefaultEquals == johnWithDefaultEquals

        johnWithDefaultEquals.hashCode() == johnWithDefaultEquals.hashCode()
        johnWithDefaultEquals.hashCode() != new NameWithDefaultEquals("John").hashCode()

        john != jim
        john == new Name("John")

        john.hashCode() == new Name("John").hashCode()
        john.hashCode() == "John".hashCode()
        john.hashCode() != jim.hashCode()

        john != john25
        john25 == new NameAndAge("John", 25)
        john25 != new NameAndAge("John", 22)
        john25 != new NameAndAge("Jim", 25)

        john25.hashCode() == new NameAndAge("John", 25).hashCode()
    }
}
