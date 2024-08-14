package com.example.demo.spring.data.jpa.queryMethods

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class QueryMethodsSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentRepository studentRepository

    def "should test search"() {
        given:
        studentRepository.saveAll([john, jim, michael])

        expect:
        studentRepository.findNameById(john.getId()) == "John"
        studentRepository.findAllAsDTOs().size() == 3
        studentRepository.findByIdAsDTOs(jim.getId()).getName() == "Jim"
        studentRepository.findByAddressStreetName(john.getAddress().getStreetName()).get(0).getName() == "John"
        studentRepository.findNameAndAgeById(jim.getId()) == "Jim,21"
        studentRepository.findByAddressStreetNameAndFlatNumber(michael.getAddress().getStreetName(), michael.getAddress().getFlatNumber()).get(0).getName() == "Michael"
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