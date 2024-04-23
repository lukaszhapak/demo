package com.example.demo.test.unit.testingApproach.implementation

import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class StudentFacadeSpec extends Specification {

    StudentRepository studentRepository = Mock()
    StudentEventPublisher messagePublisher = Mock()
    StudentValidator studentValidator = Mock()
    StudentMapper studentMapper = Mock()
    StudentFacade studentFacade = new StudentFacade(studentRepository, messagePublisher, studentValidator, studentMapper)
    StudentDTO studentDTO = new StudentDTO("John", 21)
    Student student = new Student(1, "John", 21)

    void setup() {
        studentMapper.toDomain(_ as StudentDTO) >> student
        studentMapper.toDTO(_ as Student) >> studentDTO
    }

    def "should save valid student"() {
        given:
        studentRepository.save(_ as Student) >> student

        when:
        StudentDTO response = studentFacade.save(studentDTO)

        then:
        assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(studentDTO)
        1 * messagePublisher.publishStudentSavedEvent(_)
        1 * studentRepository.save(_)
    }

    def "should throw exception when student is invalid"() {
        given:
        studentValidator.validate(_ as Student) >> { throw new IllegalArgumentException() }

        when:
        studentFacade.save(studentDTO)

        then:
        thrown IllegalArgumentException
        0 * messagePublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }
}
