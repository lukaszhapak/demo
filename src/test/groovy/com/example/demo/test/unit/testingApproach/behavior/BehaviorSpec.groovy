package com.example.demo.test.unit.testingApproach.behavior

import spock.lang.Specification

class BehaviorSpec extends Specification {

    StudentRepository studentRepository = Mock()
    StudentEventPublisher messagePublisher = Mock()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher)
    StudentDTO student = new StudentDTO("John", 21)

    def "should save valid student"() {
        when:
        StudentDTO response = studentFacade.save(student)

        then:
        response.getName() == "John"
        1 * messagePublisher.publishStudentSavedEvent(_)
        1 * studentRepository.save(_)
    }

    def "should throw exception when name is too short"() {
        given:
        student.setName("J")

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }

    def "should throw exception when age is too high"() {
        given:
        student.setAge(131)

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }
}
