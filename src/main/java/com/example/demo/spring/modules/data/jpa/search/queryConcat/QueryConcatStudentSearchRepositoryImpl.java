package com.example.demo.spring.modules.data.jpa.search.queryConcat;

import com.example.demo.spring.base.dto.StudentSearchCriteria;
import com.example.demo.spring.base.entity.Student;
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
class QueryConcatStudentSearchRepositoryImpl implements QueryConcatStudentSearchRepository {

  private final EntityManager em;

  @Override
  public List<Student> findAll(StudentSearchCriteria criteria) {

	List<String> jpqlParts = new ArrayList<>();
//	jpqlParts.add("SELECT NEW com.example.demo.spring.base.dto.StudentDTO(s.id, s.firstName, s.lastName, s.age)");
	jpqlParts.add("SELECT s");

	jpqlParts.add("FROM Student s WHERE 1=1");

	Map<String, Object> paramMap = new HashMap<>();

	if (StringUtils.isNotEmpty(criteria.getSearch())) {
	  jpqlParts.add("AND ( UPPER(s.firstName) LIKE UPPER('%' || :search || '%')");
	  jpqlParts.add("OR UPPER(s.lastName) LIKE UPPER('%' || :search || '%')");
	  jpqlParts.add("OR UPPER(s.address.streetName) LIKE UPPER('%' || :search || '%'))");
	  paramMap.put("search", criteria.getSearch());
	}

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
	if (criteria.getSortBy() != null) {
	  jpqlParts.add("ORDER BY " + criteria.getSortBy());
	  jpqlParts.add(criteria.getSortAscending() == null || criteria.getSortAscending() ? "ASC" : "DESC");
	}
	String jqpl = String.join(" ", jpqlParts);
	TypedQuery<Student> query = em.createQuery(jqpl, Student.class)
		.setFirstResult(criteria.getPage() * criteria.getSize())
		.setMaxResults(criteria.getSize());
	for (String paramName : paramMap.keySet()) {
	  query.setParameter(paramName, paramMap.get(paramName));
	}

	// todo  executing count can be heavy comparing to fetching list of records
//	new PageImpl(
//		null,
//		null,
//		null);

	return query.getResultList();
  }
}
