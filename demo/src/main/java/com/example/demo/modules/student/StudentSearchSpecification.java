package com.example.demo.modules.student;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@AllArgsConstructor
class StudentSearchSpecification implements Specification<StudentEntity> {

  private StudentSearchCriteria searchCriteria;

  @Override
  public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query,
	  CriteriaBuilder criteriaBuilder) {
	List<Predicate> predicates = new LinkedList<>();

	if (searchCriteria.getId() != null) {
	  predicates.add(criteriaBuilder.equal(root.get("id"), searchCriteria.getId()));
	}
	if (searchCriteria.getName() != null) {
	  predicates.add(criteriaBuilder.like(root.get("name"), searchCriteria.getName()));
	}
	if (searchCriteria.getMinAge() != null) {
	  predicates.add(
		  criteriaBuilder.greaterThanOrEqualTo(root.get("age"), searchCriteria.getMinAge()));
	}
	if (searchCriteria.getMaxAge() != null) {
	  predicates.add(
		  criteriaBuilder.lessThanOrEqualTo(root.get("age"), searchCriteria.getMaxAge()));
	}
	CriteriaQuery<?> grades = query
		.where(predicates.toArray(new Predicate[predicates.size()]))
		.select(root.get("grades"));

//	return grades.getRestriction();
	return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//	return 	query.select(root.get("grades")).where(predicates.toArray(new Predicate[predicates.size()])).getGroupRestriction();
  }
}
