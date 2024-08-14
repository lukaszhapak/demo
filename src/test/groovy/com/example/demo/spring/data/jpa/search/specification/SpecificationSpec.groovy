package com.example.demo.spring.data.jpa.search.specification

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import java.time.LocalDateTime

import static org.assertj.core.api.AssertionsForClassTypes.assertThat

class SpecificationSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentService studentService

    def "should test search"() {
        given:
        saveStudents(john, jim, michael)

        expect:
        studentService.getStudents(getCriteria().setSize(1)).content.size() == 1
        studentService.getStudents(getCriteria().setSize(2)).content.size() == 2
        studentService.getStudents(getCriteria().setSortBy("age")).content.get(0).firstName == "Jim"
        studentService.getStudents(getCriteria().setSortBy("age")).content.get(1).firstName == "John"
        studentService.getStudents(getCriteria().setSortBy("age").setSortAscending(false)).content.get(0).firstName == "Michael"
        studentService.getStudents(getCriteria().setSize(1).setPage(1).setSortBy("age").setSortAscending(false)).content.get(0).firstName == "John"
        assertPageContains(studentService.getStudents(getCriteria().setFirstName("Jim")), [jim])
        assertPageContains(studentService.getStudents(getCriteria().setStreetName("Oak street")), [john])
        assertPageContains(studentService.getStudents(getCriteria().setOlderThan(21)), [john, michael])
        assertPageContains(studentService.getStudents(getCriteria().setMinimalAge(24)), [john, michael])
        assertPageContains(studentService.getStudents(getCriteria().setLastNames(["Doe", "Newman"])), [john, jim])
        assertPageContains(studentService.getStudents(getCriteria().setLastNames(Collections.emptyList())), [john, jim, michael])
        assertPageContains(studentService.getStudents(getCriteria().setDateBefore(LocalDateTime.of(2024, 2, 25, 0, 0, 0))), [jim])
        assertPageContains(studentService.getStudents(getCriteria().setDateAfter(LocalDateTime.of(2024, 2, 25, 0, 0, 0))), [michael])
    }

    def assertPageContains(Page<Student> page, List<Student> students) {
        assertThat(page.getContent()).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("id").isEqualTo(students)
    }

    def saveStudents(Student... students) {
        for (Student student : students) {
            studentService.save(student)
        }
    }

    Student john = new Student()
            .setFirstName("John")
            .setLastName("Doe")
            .setAge(24)
            .setDate(LocalDateTime.of(2024, 2, 25, 0, 0, 0))
            .setAddress(new Address()
                    .setStreetName("Oak street")
                    .setFlatNumber("51"))

    Student jim = new Student()
            .setFirstName("Jim")
            .setLastName("Newman")
            .setAge(21)
            .setDate(LocalDateTime.of(2024, 2, 10, 0, 0, 0))
            .setAddress(new Address()
                    .setStreetName("School street")
                    .setFlatNumber("12"))

    Student michael = new Student()
            .setFirstName("Michael")
            .setLastName("Smith")
            .setAge(27)
            .setDate(LocalDateTime.of(2024, 3, 1, 0, 0, 0))
            .setAddress(new Address()
                    .setStreetName("Student street")
                    .setFlatNumber("123"))

    def getCriteria() {
        new StudentSearchCriteria()
                .setPage(0)
                .setSize(10)
    }
}
