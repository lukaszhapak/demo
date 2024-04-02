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

    def setup() {
        saveStudents(john, jim, michael)
    }

    def cleanup() {
        studentService.deleteAll()
    }

    def "should get student by first name"() {
        given:
        studentSearchCriteria.setFirstName("Jim")

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertPageContains(students, jim)
    }

    def "should get student by street name"() {
        given:
        studentSearchCriteria.setStreetName("Oak street")

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertPageContains(students, john)
    }

    def "should get student by older than"() {
        given:
        studentSearchCriteria.setOlderThan(21)

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertPageContains(students, List.of(john, michael))
    }

    def "should get student by minimal age"() {
        given:
        studentSearchCriteria.setMinimalAge(24)

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertPageContains(students, List.of(john, michael))
    }

    def "should get student by last names"() {
        given:
        studentSearchCriteria.setLastNames(List.of("Doe", "Newman"))

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertPageContains(students, List.of(john, jim))
    }

    def "should get all students when last names list is empty"() {
        given:
        studentSearchCriteria.setLastNames(Collections.emptyList())

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertPageContains(students, List.of(john, jim, michael))
    }

    def "should get student by date before"() {
        given:
        studentSearchCriteria.setDateBefore(LocalDateTime.of(2024, 2, 25, 0, 0, 0))

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertPageContains(students, jim)
    }

    def "should get student by date after"() {
        given:
        studentSearchCriteria.setDateAfter(LocalDateTime.of(2024, 2, 25, 0, 0, 0))

        when:
        Page<Student> students = studentService.getStudents(studentSearchCriteria)

        then:
        assertPageContains(students, michael)
    }

    def assertPageContains(Page<Student> page, List<Student> students) {
        assertThat(page.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(students)
    }

    def assertPageContains(Page<Student> page, Student student) {
        assertThat(page.getContent()).usingRecursiveComparison().ignoringCollectionOrder().isEqualTo(List.of(student))
    }

    def saveStudents(Student... students) {
        for (Student student : students) {
            studentService.save(student);
        }
    }
}
