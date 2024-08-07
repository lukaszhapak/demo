package com.example.demo.spring.data.mongo.mongoTemplate


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class MongoTemplateSpec extends Specification {

    @Autowired
    StudentService studentService

    def "should save and fetch student"() {
        when:
        String id = studentService.save(createStudent()).id

        then:
        studentService.findById(id).name == "John"
    }

    Student createStudent() {
        new Student()
                .setName("John")
                .setAge(20)
    }
}
