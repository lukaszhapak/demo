package com.example.demo.test.unit.inMemoryImplementation.mock

import spock.lang.Specification

class MockSpec extends Specification {

    StudentEventPublisher studentEventPublisher = Mock()
    StudentRepository studentRepository = Mock()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, studentEventPublisher)
    StudentDTO student = new StudentDTO(12, "John", 22)

    def "should save valid student and publish event"() {
        when:
        studentFacade.save(student).getId()

        then:
        1 * studentEventPublisher.publishStudentSavedEvent(_)
    }

    def "should get by id"() {
        given:
        studentRepository.findById(12) >> new Student(12, "John", 22)

        expect:
        studentFacade.findById(12).getName() == 'John'
    }

    def "should get by name"() {
        given:
        studentRepository.findByName("John") >> new Student(12, "John", 22)

        expect:
        studentFacade.findByName("John").getName() == 'John'
    }

    def "should throw exception when name is too short"() {
        given:
        student.setName("J")

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * studentEventPublisher.publishStudentSavedEvent(_)
    }

    def "should throw exception when age is too high"() {
        given:
        student.setAge(131)

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * studentEventPublisher.publishStudentSavedEvent(_)
    }
}
