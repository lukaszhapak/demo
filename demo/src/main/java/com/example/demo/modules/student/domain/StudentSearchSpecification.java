package com.example.demo.modules.student.domain;

import com.example.demo.modules.student.repository.StudentEntity;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@AllArgsConstructor
public class StudentSearchSpecification implements Specification<StudentEntity> {

  private Integer page;
  private Integer size;
  private String sortBy;
  private Boolean sortAscending;

  private Integer id;
  private Integer minAge;
  private Integer maxAge;
  private String name;

  @Override
  public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	List<Predicate> predicates = new LinkedList<>();

	if (id != null) {
	  predicates.add(criteriaBuilder.equal(root.get("id"), id));
	}
	if (name != null) {
	  predicates.add(criteriaBuilder.like(root.get("name"), name));
	}
	if (minAge != null) {
	  predicates.add(
		  criteriaBuilder.greaterThanOrEqualTo(root.get("age"), minAge));
	}
	if (maxAge != null) {
	  predicates.add(
		  criteriaBuilder.lessThanOrEqualTo(root.get("age"), maxAge));
	}
	return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
  }
}
