package com.example.demo.spring.web.testHttpClients;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  Student findById(Long id) {
	log.debug("get student id={}", id);
	return studentRepository.findById(id).orElseThrow();
  }

  List<Student> findAll() {
	log.debug("get students");
	return studentRepository.findAll();
  }

  Student save(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  Student update(Long id, Student student) {
	log.debug("update student id={}, student={}", id, student);
	student.setId(id);
	return studentRepository.save(student);
  }

  void deleteById(Long id) {
	log.debug("delete student id={}", id);
	studentRepository.deleteById(id);
  }
}
