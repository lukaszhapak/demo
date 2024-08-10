package com.example.demo.spring.http.server.thymeleaf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  List<Student> findAll() {
	log.debug("getting all students");
	return studentRepository.findAll();
  }

  Student findById(long id) {
	log.debug("getting student by id={}", id);
	return studentRepository.findById(id).orElse(null);
  }

  void save(Student student) {
	log.debug("saving student={}", student);
	studentRepository.save(student);
  }

  void deleteById(long id) {
	log.debug("deleting student by id={}", id);
	studentRepository.deleteById(id);
  }
}

