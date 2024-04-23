package com.example.demo.test.unit.inMemoryImplementation.inMemoryImplementation


import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class InMemoryImplementationSpec extends Specification {

    StudentEventPublisher messagePublisher = Mock()
    StudentRepository studentRepository = new StudentInMemoryRepository()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher)
    StudentDTO student = new StudentDTO("John", 21)

    def "should save and get valid student"() {
        when:
        StudentDTO response = studentFacade.save(student)

        then:
        assertThat(studentFacade.findById(response.getId())).usingRecursiveComparison().ignoringFields("id").isEqualTo(student)
        assertThat(studentFacade.findByName(student.getName())).usingRecursiveComparison().ignoringFields("id").isEqualTo(student)
        1 * messagePublisher.publishStudentSavedEvent(_)
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
