package com.example.demo.test.unit.testingApproach.behavior

import spock.lang.Specification

class ThirdBehaviorSpec extends Specification {

    StudentRepository studentRepository = Mock()
    StudentEventPublisher studentEvenPublisher = Mock()

    StudentConfigurationWithFields studentConfig = new StudentConfigurationWithFields(studentRepository, studentEvenPublisher)

    StudentFacade studentFacade = studentConfig.studentFacade()
    StudentDTO student = new StudentDTO("John", 21)

    def "should save valid student"() {
        when:
        StudentDTO response = studentFacade.save(student)

        then:
        response.getName() == "John"
        1 * studentEvenPublisher.publishStudentSavedEvent(_)
        1 * studentRepository.save(_)
    }

    def "should throw exception when name is too short"() {
        given:
        student.setName("J")

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * studentEvenPublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }

    def "should throw exception when age is too high"() {
        given:
        student.setAge(131)

        when:
        studentFacade.save(student)

        then:
        thrown IllegalArgumentException
        0 * studentEvenPublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }
}
