package com.example.demo.test.unit.inMemoryImplementation.mock


import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class MockSpec extends Specification {

    StudentEventPublisher messagePublisher = Mock()
    StudentRepository studentRepository = Mock()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher)
    StudentDTO student = new StudentDTO(12, "John", 22)

    def "should save valid student"() {
        when:
        studentRepository.save(_) >> new Student(12, "John", 22)
        StudentDTO response = studentFacade.save(student)

        then:
        assertThat(response).usingRecursiveComparison().isEqualTo(student)
        1 * messagePublisher.publishStudentSavedEvent(_)
    }

    def "should get by name"() {
        given:
        studentRepository.findByName("John") >> new Student(12, "John", 22)

        expect:
        assertThat(studentFacade.findByName(student.getName())).usingRecursiveComparison().isEqualTo(student)
    }


    def "should get by id"() {
        given:
        studentRepository.findById(12) >> new Student(12, "John", 22)

        expect:
        assertThat(studentFacade.findById(12)).usingRecursiveComparison().isEqualTo(student)
    }

    def "should throw exception when name is too short"() {
        given:
        student.setName("J")

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(_)
    }

    def "should throw exception when age is too high"() {
        given:
        student.setAge(131)

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(_)
    }
}
