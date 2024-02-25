package com.example.demo.spring.data.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

interface StudentRepository extends MongoRepository<Student, String> {

}
