package com.example.demo.spring.jpql;

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

  public String findNameById(Long id) {
	log.debug("get student name by id={}", id);
	return studentRepository.findNameById(id);
  }

  public List<StudentDTO> findAllAsDTOs() {
	log.debug("get students as DTOs");
	return studentRepository.findAllAsDTOs();
  }

  public Student findByAddressStreetName(String streetName){
	log.debug("get student by streetName={}", streetName);
	Student student = studentRepository.findByAddressStreetName(streetName);
	log.debug("returning student={}", student);
	return student;
  }
}