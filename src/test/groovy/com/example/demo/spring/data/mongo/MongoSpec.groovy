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
        studentRepository.findById(student.id).get().name == student.name
    }

    Student createStudent() {
        new Student()
                .setId(UUID.randomUUID().toString())
                .setName("John")
                .setAge(20)
                .setCreated(LocalDateTime.now())
    }
}
