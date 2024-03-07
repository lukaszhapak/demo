package com.example.demo.test.refactoring.pitest


import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class StudentFacadeSpec extends Specification {

    StudentRepository studentRepository = Mock()
    MessagePublisher messagePublisher = Mock()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher)
    Student student = new Student("John", 24)

    def "should save valid student"() {
        when:
        Student response = studentFacade.save(student)

        then:
        assertThat(response).usingRecursiveComparison().isEqualTo(student)
        1 * messagePublisher.publishStudentSavedEvent(student)
        1 * studentRepository.save(student)
    }

    def "should throw exception when name is too short"() {
        given:
        student.setName("J")

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(student)
        0 * studentRepository.save(student)
    }

    def "should throw exception when age is too high"() {
        given:
        student.setAge(131)

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(student)
        0 * studentRepository.save(student)
    }
}
