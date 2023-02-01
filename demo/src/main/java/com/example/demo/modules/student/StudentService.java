package com.example.demo.modules.student;

import com.example.demo.exception.NotFoundException;
import com.example.demo.modules.student.api.Student;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;
  private final StudentValidator studentValidator;

  public Student getStudentById(Long id) {
	log.trace("Get student with Id={}", id);
	return studentRepository.findStudentById(id).orElseThrow(() -> new NotFoundException(
		MessageFormat.format("Student with id:{0} not found", id))).toDomain();
  }

  public Student saveStudent(Student student) {
	log.debug("Save student Student={}", student);
	studentValidator.validate(student);
	StudentEntity studentEntity = StudentEntity.of(student);
	return studentRepository.save(studentEntity).toDomain();
  }

  public Long calculateStatistics() {
	log.trace("Get students count");
	return studentRepository.count();
  }

  public void deleteStudentById(Long id) {
	if (studentRepository.deleteAllById(id) == 0) {
	  throw new NotFoundException(
		  MessageFormat.format("Student with id:{0} not found", id));
	}
  }
}
