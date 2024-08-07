package com.example.demo.spring.data.mongo.mongoTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StudentService {

  private final MongoTemplate mongoTemplate;

  Student save(Student student) {
	return mongoTemplate.save(student);
  }

  Student findById(String id) {
	return mongoTemplate.findById(id, Student.class);
  }
}
