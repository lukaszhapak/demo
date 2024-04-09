package com.example.demo.test.unit.pitest


import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class StudentFacadeSpec extends Specification {

    StudentRepository studentRepository = Mock()
    StudentEventPublisher messagePublisher = Mock()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher)
    Student validStudent = new Student("John", 24)

    def "should save valid student"() {
        when:
        Student response = studentFacade.save(validStudent)

        then:
        assertThat(response).usingRecursiveComparison().isEqualTo(validStudent)
        1 * messagePublisher.publishStudentSavedEvent(validStudent)
        1 * studentRepository.save(validStudent)
    }

    def "should throw exception when name is too short"() {
        given:
        validStudent.setName("J")

        when:
        studentFacade.save(validStudent)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(validStudent)
        0 * studentRepository.save(validStudent)
    }

    def "should throw exception when age is too high"() {
        given:
        validStudent.setAge(131)

        when:
        studentFacade.save(validStudent)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(validStudent)
        0 * studentRepository.save(validStudent)
    }
}
