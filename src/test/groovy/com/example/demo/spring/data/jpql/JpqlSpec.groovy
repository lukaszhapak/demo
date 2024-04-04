package com.example.demo.spring.data.jpql


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class JpqlSpec extends Specification {

    @Autowired
    StudentRepository studentRepository

    def setup() {
        studentRepository.saveAll(List.of(john, jim, michael))
    }

    def cleanup() {
        studentRepository.deleteAll()
    }

    def "should fetch name"() {
        expect:
        studentRepository.findNameById(john.getId()) == "John"
    }

    def "should find as DTOs"() {
        expect:
        studentRepository.findAllAsDTOs().size() == 3
    }

    def "should find by id as DTOs"() {
        expect:
        studentRepository.findByIdAsDTOs(jim.getId()).getName() == "Jim"
    }

    def "should find by street name"() {
        expect:
        studentRepository.findByAddressStreetName(john.getAddress().getStreetName()).get(0).getName() == "John"
    }

    def "should get name and age"() {
        expect:
        studentRepository.findNameAndAgeById(jim.getId()) == "Jim,21"
    }

    def "should get student by street name and flat number"() {
        expect:
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