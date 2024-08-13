package com.example.demo.spring.data.jpa.search.queryConcat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

@Repository
@RequiredArgsConstructor
class StudentRepository {

  private final EntityManager em;


  public List<Student> findAll(StudentSearchCriteria criteria) {

	// todo page request
//	Pageable pageRequest = PageRequest.of(
//		criteria.getPage(),
//		criteria.getSize(),
//		Sort.by(criteria.sortAscending == null || criteria.sortAscending ? Direction.ASC : Direction.DESC,
//			criteria.getSortBy()));

	// it can fetch dto from db, with specification it ss not possible

	List<String> jpqlParts = new ArrayList<>();
	jpqlParts.add("SELECT s" +
		" FROM Student s " +
		" WHERE 1=1");

	Map<String, Object> paramMap = new HashMap<>();

	if (StringUtils.isNotEmpty(criteria.getFirstName())) {
	  jpqlParts.add("AND UPPER(s.firstName) LIKE UPPER('%' || :firstName || '%')");
	  paramMap.put("firstName", criteria.getFirstName());
	}
	if (StringUtils.isNotEmpty(criteria.getStreetName())) {
	  jpqlParts.add("AND s.address.streetName = :streetName");
	  paramMap.put("streetName", criteria.getStreetName());
	}
	if (criteria.getOlderThan() != null) {
	  jpqlParts.add("AND s.age > :olderThan");
	  paramMap.put("olderThan", criteria.getOlderThan());
	}
	if (criteria.getMinimalAge() != null) {
	  jpqlParts.add("AND s.age >= :minimalAge");
	  paramMap.put("minimalAge", criteria.getMinimalAge());
	}
	if (!CollectionUtils.isEmpty(criteria.getLastNames())) {
	  jpqlParts.add("AND s.lastName in :lastNames");
	  paramMap.put("lastNames", criteria.getLastNames());
	}
	if (criteria.getDateBefore() != null) {
	  jpqlParts.add("AND s.date < :dateBefore");
	  paramMap.put("dateBefore", criteria.getDateBefore());
	}
	if (criteria.getDateAfter() != null) {
	  jpqlParts.add("AND s.date > :dateAfter");
	  paramMap.put("dateAfter", criteria.getDateAfter());
	}
	String jqpl = String.join(" ", jpqlParts);
	TypedQuery<Student> query = em.createQuery(jqpl, Student.class);
	for (String paramName : paramMap.keySet()) {
	  query.setParameter(paramName, paramMap.get(paramName));
	}
	return query.getResultList();
  }

  public Student save(Student student) {
	return em.merge(student);
  }
}
