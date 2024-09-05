package com.example.demo.spring.modules.data.mongo.mongoTemplate

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.modules.data.mongo.base.MongoStudent
import org.springframework.beans.factory.annotation.Autowired

class MongoTemplateSpec extends AbstractIntegrationSpec {

    @Autowired
    MongoTemplateStudentRepository studentRepository

    def "should save and fetch student"() {
        when:
        String id = studentRepository.save(createStudent()).id

        then:
        studentRepository.findById(id).age == 20
        studentRepository.updateAge(id, 32)
        studentRepository.findByFirstName("John").age == 32
        studentRepository.findByAgeGreaterThan(18).firstName == "John"
        studentRepository.findByAgeGreaterThan(50) == null
    }

    MongoStudent createStudent() {
        new MongoStudent()
                .setFirstName("John")
                .setAge(20)
    }
}
