package com.example.demo.spring.data.mongo.mongoSearch

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

import static org.assertj.core.api.AssertionsForClassTypes.assertThat

class MongoSearchSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentRepository studentRepository

    def "should test search"() {
        given:
        saveStudents(john, jim, michael)

        expect:
        assertPageContains(studentRepository.getStudents(getCriteria().setFirstName("Jim")), [jim])
        assertPageContains(studentRepository.getStudents(getCriteria().setStreetName("Oak street")), [john])
        assertPageContains(studentRepository.getStudents(getCriteria().setOlderThan(21)), [john, michael])
        assertPageContains(studentRepository.getStudents(getCriteria().setMinimalAge(24)), [john, michael])
        assertPageContains(studentRepository.getStudents(getCriteria().setLastNames(["Doe", "Newman"])), [john, jim])
        assertPageContains(studentRepository.getStudents(getCriteria().setLastNames(Collections.emptyList())), [john, jim, michael])
        assertPageContains(studentRepository.getStudents(getCriteria().setDateBefore(LocalDateTime.of(2024, 2, 25, 0, 0, 0))), [jim])
        assertPageContains(studentRepository.getStudents(getCriteria().setDateAfter(LocalDateTime.of(2024, 2, 25, 0, 0, 0))), [michael])
    }

    def assertPageContains(List<Student> page, List<Student> students) {
        assertThat(page).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("id").isEqualTo(students)
    }

    def saveStudents(Student... students) {
        for (Student student : students) {
            studentRepository.save(student)
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
                .setSortBy("id")
    }
}
