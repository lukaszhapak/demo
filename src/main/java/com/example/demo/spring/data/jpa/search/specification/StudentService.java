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

  Page<Student> getStudents(StudentSearchCriteria studentSearchCriteria) {
	log.debug("getting students studentSearchCriteria={}", studentSearchCriteria);
	Pageable pageRequest = PageRequest.of(
		studentSearchCriteria.getPage(),
		studentSearchCriteria.getSize(),
		Sort.by(studentSearchCriteria.sortAscending == null || studentSearchCriteria.sortAscending ? Direction.ASC : Direction.DESC,
			studentSearchCriteria.getSortBy()));
	return studentRepository.findAll(new StudentSearchSpecification(studentSearchCriteria), pageRequest);
  }

  Student save(Student student) {
	log.debug("saving student={}", student);
	return studentRepository.save(student);
  }
}
