package com.example.demo.test.unit.inMemoryImplementation.inMemoryImplementation

import spock.lang.Specification

class InMemoryImplementationSpec extends Specification {

    StudentEventPublisher studentEventPublisher = new InMemoryStudentEventPublisher()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(new InMemoryStudentRepository(), studentEventPublisher)
    StudentDTO student = new StudentDTO("John", 22)

    def "should save valid student and publish event"() {
        when:
        StudentDTO response = studentFacade.save(student)

        then:
        response.getName() == "John"
        studentEventPublisher.events.size() == 1
        studentEventPublisher.events.get(0).getName() == "John"
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
}
