package com.example.demo.spring.data.jpa.search.queryConcat;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  List<Student> getStudents(StudentSearchCriteria studentSearchCriteria) {
	log.debug("getting students studentSearchCriteria={}", studentSearchCriteria);
	return studentRepository.findAll(studentSearchCriteria);
  }

  @Transactional
  public Student save(Student student) {
	log.debug("saving student={}", student);
	return studentRepository.save(student);
  }
}
