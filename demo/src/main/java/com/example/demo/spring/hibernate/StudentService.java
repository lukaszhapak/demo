package com.example.demo.spring.hibernate;

import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

  public List<Student> findAll() {
	log.debug("get students");
	return studentRepository.findAll();
  }

  public Student save(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  public String findFirstNameById(Long id) {
	log.debug("get student name by id={}", id);
	return studentRepository.findFirstNameById(id);
  }

  public List<StudentDTO> findAllAsDTOs() {
	log.debug("get students as DTOs");
	return studentRepository.findAllAsDTOs();
  }
}