package com.example.demo.test.unit.inMemoryImplementation.inMemoryImplementation

import spock.lang.Specification

class InMemoryImplementationSpec extends Specification {

    StudentEventPublisher messagePublisher = Mock()
    StudentRepository studentRepository = new InMemoryStudentRepository()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher)
    StudentDTO student = new StudentDTO("John", 22)

    def "should save valid student and publish event"() {
        when:
        StudentDTO response = studentFacade.save(student)

        then:
        response.getName() == "John"
        1 * messagePublisher.publishStudentSavedEvent(_)
    }

    def "should get by id"() {
        given:
        Long id = studentFacade.save(student).getId()

        expect:
        studentFacade.findById(id).getName() == 'John'
    }

    def "should get by name"() {
        given:
        studentFacade.save(student)

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
