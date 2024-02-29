package com.example.demo.nonspring.test


import spock.lang.Specification

import static org.assertj.core.api.Assertions.assertThat

class BasicSpockTest extends Specification {
    NumberService numberService = new NumberService();
    NumberService numberServiceMock = Mock();

    StudentRepository studentRepository = Mock();
    StudentService studentService = new StudentService(studentRepository);

    def "should get number from service"() {
        expect:
        numberService.returningInt() == 5
    }


    def "should get number from mocked service"() {
        given:
        numberServiceMock.returningInt() >> 3

        when:
        int number = numberServiceMock.returningInt()

        then:
        number == 3
    }

    def "should verify call on mocked service"() {
        when:
        numberServiceMock.returningInt()

        then:
        1 * numberServiceMock.returningInt()
    }

    def "should verify call with argument on mocked service"() {
        when:
        numberServiceMock.returningInt(5)

        then:
        1 * numberServiceMock.returningInt(5)
    }

    def "should compare student and studentDTO"() {
        given:
        StudentDTO studentDTO = new StudentDTO("John", 24)
        Student student = new Student(1, "John", 24)

        expect:
        assertThat(studentDTO).usingRecursiveComparison().isEqualTo(student)
    }

    def "should save student"() {
        given:
        Student student = new Student(1, "John", 24)

        when:
        studentService.save(student)

        then:
        1 * studentRepository.save({student.name == "John" && student.age == 24})
    }
}
