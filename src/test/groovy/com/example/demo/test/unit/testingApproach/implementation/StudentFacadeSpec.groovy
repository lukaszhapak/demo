package com.example.demo.test.unit.testingApproach.implementation

import spock.lang.Specification

class StudentFacadeSpec extends Specification {

    StudentRepository studentRepository = Mock()
    StudentEventPublisher studentEventPublisher = Mock()
    StudentValidator studentValidator = Mock()
    StudentMapper studentMapper = Mock()
    StudentFacade studentFacade = new StudentFacade(studentRepository, studentEventPublisher, studentValidator, studentMapper)
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
        response.getName() == "John"
        1 * studentEventPublisher.publishStudentSavedEvent(_)
        1 * studentRepository.save(_)
    }

    def "should throw exception when student is invalid"() {
        given:
        studentValidator.validate(_ as Student) >> { throw new IllegalArgumentException() }

        when:
        studentFacade.save(studentDTO)

        then:
        thrown IllegalArgumentException
        0 * studentEventPublisher.publishStudentSavedEvent(_)
        0 * studentRepository.save(_)
    }
}
