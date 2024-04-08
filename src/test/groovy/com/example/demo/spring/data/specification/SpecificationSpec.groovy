package com.example.demo.spring.data.specification

import com.example.demo.commons.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page

import java.time.LocalDateTime

import static org.assertj.core.api.AssertionsForClassTypes.assertThat

class SpecificationSpec extends IntegrationSpec {

    @Autowired
    StudentService studentService

    def setup() {
        saveStudents(john, jim, michael)
    }

    def cleanup() {
        studentService.deleteAll()
    }

    def "should test search"() {
        expect:
        assertPageContains(studentService.getStudents(criteria), students)

        where:
        criteria                                                            | students
        getCriteria().setFirstName("Jim")                                   | [jim]
        getCriteria().setStreetName("Oak street")                           | [john]
        getCriteria().setOlderThan(21)                                      | [john, michael]
        getCriteria().setMinimalAge(24)                                     | [john, michael]
        getCriteria().setLastNames(List.of("Doe", "Newman"))                | [john, jim]
        getCriteria().setLastNames(Collections.emptyList())                 | [john, jim, michael]
        getCriteria().setDateBefore(LocalDateTime.of(2024, 2, 25, 0, 0, 0)) | [jim]
        getCriteria().setDateAfter(LocalDateTime.of(2024, 2, 25, 0, 0, 0))  | [michael]
    }

    def assertPageContains(Page<Student> page, List<Student> students) {
        assertThat(page.getContent()).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("id").isEqualTo(students)
    }

    def saveStudents(Student... students) {
        for (Student student : students) {
            studentService.save(student)
        }
    }

    static Student john = Student.builder()
            .firstName("John")
            .lastName("Doe")
            .age(24)
            .date(LocalDateTime.of(2024, 2, 25, 0, 0, 0))
            .address(Address.builder()
                    .streetName("Oak street")
                    .flatNumber("51")
                    .build())
            .build()

    static Student jim = Student.builder()
            .firstName("Jim")
            .lastName("Newman")
            .age(21)
            .date(LocalDateTime.of(2024, 2, 10, 0, 0, 0))
            .address(Address.builder()
                    .streetName("School street")
                    .flatNumber("12")
                    .build())
            .build()

    static Student michael = Student.builder()
            .firstName("Michael")
            .lastName("Smith")
            .age(27)
            .date(LocalDateTime.of(2024, 3, 1, 0, 0, 0))
            .address(Address.builder()
                    .streetName("Student street")
                    .flatNumber("123")
                    .build())
            .build()

    def static getCriteria() {
        StudentSearchCriteria.builder()
                .page(0)
                .size(10)
                .sortBy("id")
                .build()
    }
}
