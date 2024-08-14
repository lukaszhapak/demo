package com.example.demo.spring.data.jpa.derivedMethods

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class DerivedMethodsSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentRepository studentRepository

    def "should test search"() {
        given:
        studentRepository.saveAll([john, jim, michael])

        expect:
        studentRepository.findById(2L, Student.class).name == "Jim"
        studentRepository.findById(2L, StudentDTO.class).name == "Jim"
        studentRepository.findById(2L, StudentInterfaceDTO.class).name == "Jim"
        studentRepository.findByAge(21).name == "Jim"
        studentRepository.findByAddressStreetName("Oak street").get(0).getName() == "John"
        studentRepository.findByAddressStreetNameAndAddressFlatNumber("Student street", "123").get(0).getName() == "Michael"
    }

    Student john = Student.builder()
            .name("John")
            .age(24)
            .address(Address.builder()
                    .streetName("Oak street")
                    .flatNumber("51")
                    .build())
            .build()

    Student jim = Student.builder()
            .name("Jim")
            .age(21)
            .address(Address.builder()
                    .streetName("School street")
                    .flatNumber("12")
                    .build())
            .build()

    Student michael = Student.builder()
            .name("Michael")
            .age(27)
            .address(Address.builder()
                    .streetName("Student street")
                    .flatNumber("123")
                    .build())
            .build()

}
