package com.example.demo.spring.modules.data.mongo.mongoSearch;

import com.example.demo.spring.base.dto.StudentSearchCriteria;
import com.example.demo.spring.modules.data.mongo.base.MongoStudent;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
@RequiredArgsConstructor
class MongoSearchStudentRepository {

  private final MongoTemplate mongoTemplate;

  MongoStudent save(MongoStudent student) {
	return mongoTemplate.save(student);
  }

  List<MongoStudent> getStudents(StudentSearchCriteria studentSearchCriteria) {
	Query query = new Query();
	Pageable pageRequest = PageRequest.of(studentSearchCriteria.getPage(), studentSearchCriteria.getSize(), Sort.by(studentSearchCriteria.getSortBy().name()));
	query.with(pageRequest);

	// todo sort direction also in specification

	if (studentSearchCriteria.getId() != null) {
	  query.addCriteria(Criteria.where("id").is(studentSearchCriteria.getId()));
	}
	if (studentSearchCriteria.getFirstName() != null) {
	  query.addCriteria(Criteria.where("firstName").is(studentSearchCriteria.getFirstName()));
	  // todo like
	}
	if (!CollectionUtils.isEmpty(studentSearchCriteria.getLastNames())) {
	  query.addCriteria(Criteria.where("lastName").in(studentSearchCriteria.getLastNames()));
	}
//	if (studentSearchCriteria.getLastNames() != null) { // if it is not null but an empty list it returns no results
//	  query.addCriteria(Criteria.where("lastName").in(studentSearchCriteria.getLastNames()));
//	}
	if (studentSearchCriteria.getStreetName() != null) {
	  query.addCriteria(Criteria.where("address.streetName").is(studentSearchCriteria.getStreetName()));
	  // todo like
	}
	if (studentSearchCriteria.getOlderThan() != null) {
	  query.addCriteria(Criteria.where("age").gt(studentSearchCriteria.getOlderThan()));
	}
	if (studentSearchCriteria.getMinimalAge() != null) {
	  query.addCriteria(Criteria.where("age").gte(studentSearchCriteria.getMinimalAge()));
	}
	if (studentSearchCriteria.getDateBefore() != null) {
	  query.addCriteria(Criteria.where("date").lt(studentSearchCriteria.getDateBefore()));
	}
	if (studentSearchCriteria.getDateAfter() != null) {
	  query.addCriteria(Criteria.where("date").gt(studentSearchCriteria.getDateAfter()));
	}
	return mongoTemplate.find(query, MongoStudent.class);
  }
}
