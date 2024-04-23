package com.example.demo.test.unit.testingApproach.implementation

import spock.lang.Specification

class StudentValidatorSpec extends Specification {

    StudentRepository studentRepository = Mock()
    StudentValidator studentValidator = new StudentValidator(studentRepository)
    Student student = new Student(1, "John", 21)

    def "should save valid student"() {
        expect:
        studentValidator.validate(student)
    }

    def "should throw exception when name is too short"() {
        given:
        student.setName("J")

        when:
        studentValidator.validate(student)

        then:
        thrown IllegalArgumentException
    }

    def "should throw exception when age is too high"() {
        given:
        student.setAge(131)

        when:
        studentValidator.validate(student)

        then:
        thrown IllegalArgumentException
    }
}
