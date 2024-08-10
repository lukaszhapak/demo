package com.example.demo.spring.data.mongo.springMongoRepository

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class SpringMongoRepositorySpec extends AbstractIntegrationSpec {

    @Autowired
    StudentRepository studentRepository

    def "should save and fetch student"() {
        when:
        String id = studentRepository.save(createStudent()).id

        then:
        studentRepository.findById(id).get().name == "John"
        studentRepository.findByName("John").name == "John"
        studentRepository.findByAgeGreaterThan(18).name == "John"
        studentRepository.findByAgeGreaterThan(50) == null
    }

    Student createStudent() {
        new Student()
                .setName("John")
                .setAge(20)
    }
}
