package com.example.demo.spring.data.hibernate;

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

  public Student findById(Long id) {
	log.debug("get student id={}", id);
	Student student = studentRepository.findById(id).orElseThrow();
	log.debug("returning student={}", student);
	return student;
  }

  public Student save(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }
}