package com.example.demo.spring.data.jpa.search.specification;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  Page<Student> getStudents(StudentSearchCriteria criteria) {
	log.debug("getting students criteria={}", criteria);
	Pageable pageRequest = PageRequest.of(
		criteria.getPage(),
		criteria.getSize(),
		Sort.by(criteria.sortAscending == null || criteria.sortAscending ? Direction.ASC : Direction.DESC,
			criteria.getSortBy() == null ? "id" : criteria.getSortBy()));
	return studentRepository.findAll(new StudentSearchSpecification(criteria), pageRequest);
  }

  Student save(Student student) {
	log.debug("saving student={}", student);
	return studentRepository.save(student);
  }
}
