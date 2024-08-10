package com.example.demo.spring.data.jpa.specification

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

    Student john = Student.builder()
            .firstName("John")
            .lastName("Doe")
            .age(24)
            .date(LocalDateTime.of(2024, 2, 25, 0, 0, 0))
            .address(Address.builder()
                    .streetName("Oak street")
                    .flatNumber("51")
                    .build())
            .build()

    Student jim = Student.builder()
            .firstName("Jim")
            .lastName("Newman")
            .age(21)
            .date(LocalDateTime.of(2024, 2, 10, 0, 0, 0))
            .address(Address.builder()
                    .streetName("School street")
                    .flatNumber("12")
                    .build())
            .build()

    Student michael = Student.builder()
            .firstName("Michael")
            .lastName("Smith")
            .age(27)
            .date(LocalDateTime.of(2024, 3, 1, 0, 0, 0))
            .address(Address.builder()
                    .streetName("Student street")
                    .flatNumber("123")
                    .build())
            .build()

    def getCriteria() {
        StudentSearchCriteria.builder()
                .page(0)
                .size(10)
                .sortBy("id")
                .build()
    }
}
