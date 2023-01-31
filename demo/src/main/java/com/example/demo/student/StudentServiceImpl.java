package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final StudentValidator studentValidator;

  @Override
  public Student getStudentById(Long id) {
	log.trace("Get student with Id={}", id);
	return Student.of(studentRepository.findStudentById(id).orElseThrow(RuntimeException::new));
  }

  @Override
  public Student saveStudent(Student student) {
	log.debug("Save student Student={}", student);
	studentValidator.validate(student);
	StudentEntity studentEntity = StudentEntity.of(student);
	return Student.of(studentRepository.save(studentEntity));
  }
}
