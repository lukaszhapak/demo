package com.example.clinic.modules.core.patient.model;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientSearchSpecification implements Specification<Patient> {

  String sortBy;
  Boolean sortAscending;
  Integer page;
  Integer size;

  Integer id;
  String firstName;
  String lastName;

  @Override
  public Predicate toPredicate(Root<Patient> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	List<Predicate> predicates = new LinkedList<>();

	if (id != null) {
	  predicates.add(criteriaBuilder.equal(root.get("id"), id));
	}
	if (firstName != null) {
	  predicates.add(criteriaBuilder.like(root.get("firstName"), firstName));
	}
	if (lastName != null) {
	  predicates.add(criteriaBuilder.like(root.get("lastName"), lastName));
	}

	return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
  }
}
