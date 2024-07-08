package com.example.demo.test.integration.product.data;

import java.util.LinkedList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@RequiredArgsConstructor
public class ProductSearchSpecification implements Specification<Product> {

  private final ProductSearchCriteria productSearchCriteria;

  @Override
  public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
	List<Predicate> predicates = new LinkedList<>();

	if (productSearchCriteria.getId() != null) {
	  predicates.add(criteriaBuilder.equal(root.get("id"), productSearchCriteria.getId()));
	}
	if (productSearchCriteria.getName() != null) {
	  predicates.add(criteriaBuilder.like(root.get("name"), productSearchCriteria.getName()));
	}
	if (productSearchCriteria.getMinIntegerValue() != null) {
	  predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("integerValue"), productSearchCriteria.getMinIntegerValue()));
	}
	if (productSearchCriteria.getMaxIntegerValue() != null) {
	  predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("integerValue"), productSearchCriteria.getMaxIntegerValue()));
	}

	return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
  }
}
