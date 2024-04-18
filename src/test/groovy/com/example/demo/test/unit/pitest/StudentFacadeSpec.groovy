package com.example.demo.test.unit.pitest


import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class StudentFacadeSpec extends Specification {

    StudentRepository studentRepository = Mock()
    StudentEventPublisher messagePublisher = Mock()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher)
    StudentDTO validStudent = new StudentDTO("John", 24)

    def "should save valid student"() {
        when:
        StudentDTO response = studentFacade.save(validStudent)

        then:
        assertThat(response).usingRecursiveComparison().isEqualTo(validStudent)
        1 * messagePublisher.publishStudentSavedEvent(_)
        1 * studentRepository.save(_)
    }

    def "should throw exception when name is too short"() {
        given:
        validStudent.setName("J")

        when:
        studentFacade.save(validStudent)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }

    def "should throw exception when age is too high"() {
        given:
        validStudent.setAge(131)

        when:
        studentFacade.save(validStudent)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }
}
