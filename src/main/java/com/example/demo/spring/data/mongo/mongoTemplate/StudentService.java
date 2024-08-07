package com.example.demo.spring.data.mongo.mongoTemplate;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

  Student findByName(String name) {
	Query query = new Query(Criteria.where("name").is(name));
	return mongoTemplate.findOne(query, Student.class);
  }
}
