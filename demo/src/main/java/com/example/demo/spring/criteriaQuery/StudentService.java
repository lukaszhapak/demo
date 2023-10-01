package com.example.demo.spring.criteriaQuery;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  Page<Student> getStudents(StudentSearchCriteria studentSearchCriteria) {
	log.debug("getting students studentSearchCriteria={}", studentSearchCriteria);
	Pageable pageRequest = PageRequest.of(studentSearchCriteria.getPage(), studentSearchCriteria.getSize(), Sort.by(studentSearchCriteria.getSortBy()));
	return studentRepository.findAll(new StudentSearchSpecification(studentSearchCriteria), pageRequest);
  }

  Student save(Student student) {
	log.debug("saving student={}", student);
	return studentRepository.save(student);
  }
}
