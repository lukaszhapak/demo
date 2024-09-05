package com.example.demo.spring.modules.data.jpa.repository.derivedMethods

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.dto.StudentDTO
import com.example.demo.spring.base.dto.StudentInterfaceDTO
import com.example.demo.spring.base.entity.Address
import com.example.demo.spring.base.entity.Student
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDateTime

class DerivedMethodsSpec extends AbstractIntegrationSpec {

    @Autowired
    DerivedMethodsStudentRepository studentRepository

    def "should test search"() {
        given:
        saveStudents(john, jim, michael)

        expect:
        studentRepository.findById(2L)

        studentRepository.findById(2L, Student.class).firstName != null
        studentRepository.findById(2L, StudentDTO.class).firstName != null
        studentRepository.findById(2L, StudentInterfaceDTO.class).firstName != null

        studentRepository.findByFirstName("John", Student.class).size() >= 1
        studentRepository.findByFirstName("John", StudentDTO.class).size() >= 1
        studentRepository.findByFirstName("John", StudentInterfaceDTO.class).size() >= 1

// todo       studentRepository.findByAge(21).firstName == "Jim"
//     todo   studentRepository.findByAge(21, Student.class).firstName == "Jim"

        studentRepository.findByAddressStreetName("Oak street", StudentDTO.class).get(0).firstName == "John"
        studentRepository.findByAddressStreetName("Oak street").get(0).firstName == "John"

        studentRepository.findByAddressStreetNameAndAddressFlatNumber("Student street", "123").get(0).firstName == "Michael"

        studentRepository.existsByFirstName("John")
        !studentRepository.existsByFirstName("Adrian")

        studentRepository.findFirst10ByOrderByAgeAsc().get(0).firstName == "Jim"
        studentRepository.findFirstByOrderByAgeAsc().firstName == "Jim"

        studentRepository.count() >= 3
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
