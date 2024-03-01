package com.example.demo.spring.data.criteriaQuery;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.CollectionUtils;

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
	if (!CollectionUtils.isEmpty(studentSearchCriteria.getLastNames())) {
	  predicates.add(criteriaBuilder.and(root.get("lastName").in(studentSearchCriteria.getLastNames())));
	}
	if (studentSearchCriteria.getStreetName() != null) {
	  predicates.add(criteriaBuilder.like(root.get("address").get("streetName"), studentSearchCriteria.getStreetName()));
	}
	if (studentSearchCriteria.getOlderThan() != null) {
	  predicates.add(criteriaBuilder.greaterThan(root.get("age"), studentSearchCriteria.getOlderThan()));
	}
	if (studentSearchCriteria.getMinimalAge() != null) {
	  predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("age"), studentSearchCriteria.getMinimalAge()));
	}
	if (studentSearchCriteria.getDateBefore() != null) {
	  predicates.add(criteriaBuilder.lessThan(root.get("date"), studentSearchCriteria.getDateBefore()));
	}
	if (studentSearchCriteria.getDateAfter() != null) {
	  predicates.add(criteriaBuilder.greaterThan(root.get("date"), studentSearchCriteria.getDateAfter()));
	}

	return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
  }
}
