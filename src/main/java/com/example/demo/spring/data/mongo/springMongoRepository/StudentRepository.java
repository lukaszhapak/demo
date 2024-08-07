package com.example.demo.spring.data.mongo.springMongoRepository;

import org.springframework.data.mongodb.repository.MongoRepository;

interface StudentRepository extends MongoRepository<Student, String> {

  Student findByName(String name);

  Student findByAgeGreaterThan(int age);
}
