package com.example.demo.spring.modules.data.mongo.mongoSearch

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.dto.StudentSearchCriteria
import com.example.demo.spring.base.dto.StudentSearchCriteriaSortBy
import com.example.demo.spring.modules.data.mongo.base.MongoAddress
import com.example.demo.spring.modules.data.mongo.base.MongoStudent

import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

import static org.assertj.core.api.AssertionsForClassTypes.assertThat

class MongoSearchSpec extends AbstractIntegrationSpec {

    @Autowired
    MongoSearchStudentRepository studentRepository

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

    def assertPageContains(List<MongoStudent> page, List<MongoStudent> students) {
        assertThat(page).usingRecursiveComparison().ignoringCollectionOrder().ignoringFields("id").isEqualTo(students)
    }

    def saveStudents(MongoStudent... students) {
        for (MongoStudent student : students) {
            studentRepository.save(student)
        }
    }

    MongoStudent john = new MongoStudent()
            .setFirstName("John")
            .setLastName("Doe")
            .setAge(24)
            .setDate(LocalDateTime.of(2024, 2, 25, 0, 0, 0))
            .setAddress(new MongoAddress()
                    .setStreetName("Oak street")
                    .setFlatNumber("51"))

    MongoStudent jim = new MongoStudent()
            .setFirstName("Jim")
            .setLastName("Newman")
            .setAge(21)
            .setDate(LocalDateTime.of(2024, 2, 10, 0, 0, 0))
            .setAddress(new MongoAddress()
                    .setStreetName("School street")
                    .setFlatNumber("12"))

    MongoStudent michael = new MongoStudent()
            .setFirstName("Michael")
            .setLastName("Smith")
            .setAge(27)
            .setDate(LocalDateTime.of(2024, 3, 1, 0, 0, 0))
            .setAddress(new MongoAddress()
                    .setStreetName("Student street")
                    .setFlatNumber("123"))

    def getCriteria() {
        new StudentSearchCriteria()
                .setPage(0)
                .setSize(10)
                .setSortBy(StudentSearchCriteriaSortBy.ID)
    }
}
