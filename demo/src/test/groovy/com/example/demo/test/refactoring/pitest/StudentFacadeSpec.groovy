package com.example.demo.test.refactoring.pitest


import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class StudentFacadeSpec extends Specification {

    StudentRepository studentRepository = Mock()
    MessagePublisher messagePublisher = Mock()
    StudentFacade studentFacade = new StudentConfiguration().studentFacade(studentRepository, messagePublisher)

    def "should save valid student"() {
        given:
        Student student = new Student("John", 24)

        when:
        Student response = studentFacade.save(student)

        then:
        assertThat(response).usingRecursiveComparison().isEqualTo(student)
        1 * studentRepository.save(student)
        1 * messagePublisher.publishStudentSavedEvent(student)
    }
}
