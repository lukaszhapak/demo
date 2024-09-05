package com.example.demo.spring.modules.data.jpa.search.queryConcat;

import com.example.demo.spring.base.dto.StudentSearchCriteria;
import com.example.demo.spring.base.entity.Student;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
class QueryConcatStudentService {

  private final QueryConcatStudentRepository studentRepository;

  List<Student> getStudents(StudentSearchCriteria studentSearchCriteria) {
	log.debug("getting students studentSearchCriteria={}", studentSearchCriteria);
	return studentRepository.findAll(studentSearchCriteria);
  }

  public Student save(Student student) {
	log.debug("saving student={}", student);
	return studentRepository.save(student);
  }
}
