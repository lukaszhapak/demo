package com.example.demo.test.unit.testingApproach.implementation

import spock.lang.Specification

class StudentMapperSpec extends Specification {

    StudentMapper studentMapper = new StudentMapper()
    Student student = new Student(1, "John", 21)
    StudentDTO studentDTO = new StudentDTO(1, "John", 21)

    def "should map to DTO"() {
        when:
        StudentDTO mapped = studentMapper.toDTO(student)

        then:
        mapped.getId() == 1
        mapped.getAge() == 21
        mapped.getName() == "John"
    }

    def "should map to domain"() {
        when:
        Student mapped = studentMapper.toDomain(studentDTO)

        then:
        mapped.getId() == 1
        mapped.getAge() == 21
        mapped.getName() == "John"
    }

}
