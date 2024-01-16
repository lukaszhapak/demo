package com.example.demo.spring.criteriaQuery;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
class StudentSearchSpecification implements Specification<Student> {

  private final StudentSearchCriteria studentSearchCriteria;

  @Override
  public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	List<Predicate> predicates = new LinkedList<>();

	if (studentSearchCriteria.getId() != null) {
	  predicates.add(criteriaBuilder.equal(root.get("id"), studentSearchCriteria.getId()));
	}
	if (studentSearchCriteria.getFirstName() != null) {
	  predicates.add(criteriaBuilder.like(root.get("firstName"), studentSearchCriteria.getFirstName()));
	}
	if (studentSearchCriteria.getLastName() != null) {
	  predicates.add(criteriaBuilder.like(root.get("lastName"), studentSearchCriteria.getLastName()));
	}
	if (studentSearchCriteria.getStreetName() != null) {
	  predicates.add(criteriaBuilder.like(root.get("address").get("streetName"), studentSearchCriteria.getStreetName()));
	}
	if (studentSearchCriteria.getOlderThan() != null) {
	  predicates.add(criteriaBuilder.greaterThan(root.get("age"), studentSearchCriteria.getOlderThan()));
	}

	return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
  }
}
