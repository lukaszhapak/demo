package com.example.demo.spring.modules.data.jpa.search.queryConcat

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.dto.StudentSearchCriteria
import com.example.demo.spring.base.entity.Address
import com.example.demo.spring.base.entity.Student
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

import static com.example.demo.spring.base.dto.StudentSearchCriteriaSortBy.AGE
import static org.assertj.core.api.AssertionsForClassTypes.assertThat

class QueryConcatSpec extends AbstractIntegrationSpec {

    @Autowired
    QueryConcatStudentService studentService

    def "should test search"() {
        given:
        saveStudents(john, jim, michael)

        expect:
        studentService.getStudents(getCriteria().setSize(1)).size() == 1
        studentService.getStudents(getCriteria().setSize(2)).size() == 2
        studentService.getStudents(getCriteria().setSortBy(AGE)).get(0).firstName == "Jim"
        studentService.getStudents(getCriteria().setSortBy(AGE).setSortAscending(false)).get(0).firstName == "Michael"
        studentService.getStudents(getCriteria().setFirstName("Ji")).stream().allMatch { it -> it.getFirstName() == "Jim" }
        studentService.getStudents(getCriteria().setStreetName("Oak street")).stream().allMatch { it -> it.address.streetName == "Oak street" }
        studentService.getStudents(getCriteria().setOlderThan(21)).stream().allMatch { it -> it.age > 21 }
        studentService.getStudents(getCriteria().setMinimalAge(24)).stream().allMatch { it -> it.age >= 24 }
        studentService.getStudents(getCriteria().setLastNames(["Doe", "Newman"])).stream().allMatch { it -> it.lastName == "Doe" || it.lastName == "Newman" }
        studentService.getStudents(getCriteria().setLastNames(Collections.emptyList())).size() >= 3
        studentService.getStudents(getCriteria().setDateBefore(LocalDateTime.of(2024, 2, 25, 0, 0, 0))).stream().allMatch {it -> it.date < LocalDateTime.of(2024, 2, 25, 0, 0, 0)}
        studentService.getStudents(getCriteria().setDateAfter(LocalDateTime.of(2024, 2, 25, 0, 0, 0))).stream().allMatch {it -> it.date > LocalDateTime.of(2024, 2, 25, 0, 0, 0)}
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