package com.example.demo.spring.data.mongo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import java.time.LocalDateTime

@SpringBootTest
class MongoSpec extends Specification {

    @Autowired
    StudentRepository studentRepository

    def "should save student"() {
        given:
        Student student = createStudent()

        when:
        studentRepository.save(student)

        then:
        studentRepository.findAll().size() >= 1
    }

    Student createStudent() {
        Student.builder()
                .id(UUID.randomUUID().toString())
                .name("John")
                .age(20)
                .created(LocalDateTime.now())
                .build()
    }
}
