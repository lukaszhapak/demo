package com.example.demo.nonspring.util.objectMapper

import spock.lang.Specification

class ObjectMapperSpec extends Specification{

    ObjectMapperService objectMapperService = new ObjectMapperService()

    def "should get student as json"() {
        expect:
        objectMapperService.getStudentAsString().contains("Jim")
    }

    def "should get student as object"() {
        expect:
        objectMapperService.getStudentAsObject().name == "John"
    }
}
