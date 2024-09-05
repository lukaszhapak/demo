package com.example.demo.spring.modules.data.mongo.springMongoRepository

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.modules.data.mongo.base.MongoStudent
import org.springframework.beans.factory.annotation.Autowired

class SpringMongoRepositorySpec extends AbstractIntegrationSpec {

    @Autowired
    SpringMongoStudentRepository studentRepository

    def "should save and fetch student"() {
        when:
        String id = studentRepository.save(createStudent()).id

        then:
        studentRepository.findById(id).get().firstName == "John"
        studentRepository.findByFirstName("John").firstName == "John"
        studentRepository.findByAgeGreaterThan(18).firstName == "John"
        studentRepository.findByAgeGreaterThan(50) == null
    }

    MongoStudent createStudent() {
        new MongoStudent()
                .setFirstName("John")
                .setAge(20)
    }
}
