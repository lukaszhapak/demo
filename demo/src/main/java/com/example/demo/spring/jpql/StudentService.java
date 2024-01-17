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

  public Student save(Student student) {
	log.debug("save student={}", student);
	return studentRepository.save(student);
  }

  public List<Student> saveAll(Iterable<Student> students) {
	log.debug("save students={}", students);
	return studentRepository.saveAll(students);
  }

  public String findNameById(Long id) {
	log.debug("get student name by id={}", id);
	return studentRepository.findNameById(id);
  }

  public String findNameAndAgeById(Long id) {
	log.debug("get student name and age by id={}", id);
	return studentRepository.findNameAndAgeById(id);
  }

  public List<StudentDTO> findAllAsDTOs() {
	log.debug("get students as DTOs");
	return studentRepository.findAllAsDTOs();
  }

  public StudentDTO findByIdAsDTOs(Long id) {
	log.debug("get student as DTO by id={}", id);
	return studentRepository.findByIdAsDTOs(id);
  }

  public List<Student> findByAddressStreetName(String streetName) {
	log.debug("get student by streetName={}", streetName);
	List<Student> students = studentRepository.findByAddressStreetName(streetName);
	log.debug("returning student={}", students);
	return students;
  }

  public List<Student> findByAddressStreetNameAndFlatNumber(String streetName, String flatNumber) {
	log.debug("get student by streetName={} and flatNumber={}", streetName, flatNumber);
	List<Student> students = studentRepository.findByAddressStreetNameAndFlatNumber(streetName, flatNumber);
	log.debug("returning student={}", students);
	return students;
  }
}