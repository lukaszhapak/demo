package com.example.demo.spring.data.specification

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Page
import spock.lang.Specification

import java.time.LocalDateTime

import static org.assertj.core.api.AssertionsForClassTypes.assertThat

@SpringBootTest
class SpecificationSpec extends Specification implements SampleData {

    @Autowired
    StudentService studentService

    void setup() {
        saveStudents(john, jim, michael)
    }

    void cleanup() {
        studentService.deleteAll()
    }

    def "should get student by first name"() {
        given:
        studentSearchCriteria.setFirstName("Jim")

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(jim))
    }

    def "should get student by street name"() {
        given:
        studentSearchCriteria.setStreetName("Oak street")

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john))
    }

    def "should get student by older than"() {
        given:
        studentSearchCriteria.setOlderThan(21)

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john, michael))
    }

    def "should get student by minimal age"() {
        given:
        studentSearchCriteria.setMinimalAge(24)

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john, michael));
    }

    def "should get student by last names"() {
        given:

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria.setLastNames(List.of("Doe", "Newman")))

        then:
        assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john, jim))
    }

    def "should get all students when last names is empty list"() {
        given:
        studentSearchCriteria.setLastNames(Collections.emptyList())

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(john, jim, michael))
    }

    def "should get student by date before"() {
        given:
        studentSearchCriteria.setDateBefore(LocalDateTime.of(2024, 2, 25, 0, 0, 0))

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(jim));
    }

    def "should get student by date after"() {
        given:
        studentSearchCriteria.setDateAfter(LocalDateTime.of(2024, 2, 25, 0, 0, 0))

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertThat(students.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(michael))
    }

    void saveStudents(Student... students) {
        for (Student student : students) {
            studentService.save(student);
        }
    }
}
