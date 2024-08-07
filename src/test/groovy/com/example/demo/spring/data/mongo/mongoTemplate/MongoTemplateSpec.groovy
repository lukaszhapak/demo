package com.example.demo.spring.data.mongo.mongoTemplate

import com.example.demo.common.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class MongoTemplateSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentService studentService

    def "should save and fetch student"() {
        when:
        String id = studentService.save(createStudent()).id

        then:
        studentService.findById(id).name == "John"
        studentService.findByName("John").name == "John"
    }

    Student createStudent() {
        new Student()
                .setName("John")
                .setAge(20)
    }
}
