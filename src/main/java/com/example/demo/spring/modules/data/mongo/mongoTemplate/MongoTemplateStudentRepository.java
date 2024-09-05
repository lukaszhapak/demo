package com.example.demo.spring.modules.data.mongo.mongoTemplate;

import com.example.demo.spring.modules.data.mongo.base.MongoStudent;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class MongoTemplateStudentRepository {

  private final MongoTemplate mongoTemplate;

  MongoStudent save(MongoStudent student) {
	return mongoTemplate.save(student);
  }

  MongoStudent findById(String id) {
	return mongoTemplate.findById(id, MongoStudent.class);
  }

  MongoStudent findByFirstName(String name) {
	Query query = new Query(Criteria.where("firstName").is(name));
	return mongoTemplate.findOne(query, MongoStudent.class);
  }

  void updateAge(String id, int age) {
	Query query = new Query(Criteria.where("id").is(id));
	Update update = new Update();
	update.set("age", age);
	UpdateResult result = mongoTemplate.updateFirst(query, update, MongoStudent.class);
  }

  MongoStudent findByAgeGreaterThan(int age) {
	Query query = new Query(Criteria.where("age").gt(age));
	return mongoTemplate.findOne(query, MongoStudent.class);
  }
}
