package com.example.demo.spring.data.mongo.mongoTemplate

import com.example.demo.common.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class MongoTemplateSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentRepository studentRepository

    def "should save and fetch student"() {
        when:
        String id = studentRepository.save(createStudent()).id

        then:
        studentRepository.findById(id).age == 20
        studentRepository.updateAge(id, 32)
        studentRepository.findByName("John").age == 32
        studentRepository.findByAgeGreaterThan(18).name == "John"
        studentRepository.findByAgeGreaterThan(50) == null
    }

    Student createStudent() {
        new Student()
                .setName("John")
                .setAge(20)
    }
}
