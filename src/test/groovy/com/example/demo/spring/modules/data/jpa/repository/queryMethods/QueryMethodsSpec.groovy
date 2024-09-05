package com.example.demo.spring.modules.data.jpa.repository.queryMethods

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.entity.Address
import com.example.demo.spring.base.entity.Student
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class QueryMethodsSpec extends AbstractIntegrationSpec {

    @Autowired
    QueryMethodsStudentRepository studentRepository

    def "should test search"() {
        given:
        saveStudents(john, jim, michael)

        expect:
        studentRepository.findFirstNameById(john.getId()) == "John"
        studentRepository.findAllAsDTOs().size() >= 3
        studentRepository.findByIdAsDTOs(jim.getId()).firstName == "Jim"
        studentRepository.findByAddressStreetName(john.getAddress().getStreetName()).get(0).firstName == "John"
        studentRepository.findFirstNameAndAgeById(jim.getId()) == "Jim,21"
        studentRepository.findByAddressStreetNameAndFlatNumber(michael.getAddress().getStreetName(), michael.getAddress().getFlatNumber()).get(0).firstName == "Michael"
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
}