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
        studentService.findById(id).age == 20
        studentService.updateAge(id, 32)
        studentService.findByName("John").age == 32
    }

    Student createStudent() {
        new Student()
                .setName("John")
                .setAge(20)
    }
}
