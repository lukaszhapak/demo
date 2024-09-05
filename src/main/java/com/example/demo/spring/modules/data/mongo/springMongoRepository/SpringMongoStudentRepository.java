package com.example.demo.spring.modules.data.mongo.springMongoRepository;

import com.example.demo.spring.modules.data.mongo.base.MongoStudent;
import org.springframework.data.mongodb.repository.MongoRepository;

interface SpringMongoStudentRepository extends MongoRepository<MongoStudent, String> {

  MongoStudent findByFirstName(String name);

  MongoStudent findByAgeGreaterThan(int age);
}
