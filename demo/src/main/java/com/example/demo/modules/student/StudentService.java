package com.example.demo.modules.student;

import com.example.demo.exception.NotFoundException;
import com.example.demo.modules.student.api.Student;
import java.text.MessageFormat;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
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
	log.debug("Get student with Id={}", id);
	return studentRepository.findStudentById(id).orElseThrow(() -> new NotFoundException(
		MessageFormat.format("Student with id:{0} not found", id))).toDomain();
  }

  public Student saveStudent(Student student) {
	log.debug("Save student Student={}", student);
	studentValidator.validate(student);
	StudentEntity studentEntity = StudentEntity.of(student);
	return studentRepository.save(studentEntity).toDomain();
  }

  public Long count() {
	log.debug("Get students count");
	return studentRepository.count();
  }

  public void deleteStudentById(Long id) {
	log.debug("Delete student with Id={}", id);
	if (studentRepository.deleteAllById(id) == 0) {
	  throw new NotFoundException(
		  MessageFormat.format("Student with id:{0} not found", id));
	}
  }

  public Student updateStudent(Long id, Student student) {
	log.debug("Update student with Id={}, student={}", id, student);
	studentValidator.validate(student);
	existsById(id);
	StudentEntity studentEntity = StudentEntity.of(student);
	studentEntity.setId(id);
	return studentRepository.save(studentEntity).toDomain();
  }

  public List<Student> findAll(StudentSearchCriteria studentSearchCriteria) {
	List<StudentEntity> all = studentRepository.findAll(
		new StudentSearchSpecification(studentSearchCriteria));
	return null;
  }

  public Student getGrades(StudentSearchCriteria studentSearchCriteria){
	List<String> studentGrades = studentRepository.getStudentGrades(
		new StudentSearchSpecification(studentSearchCriteria));

	return null;
  }

  private void existsById(Long id) {
	if (!studentRepository.existsById(id)) {
	  throw new NotFoundException(
		  MessageFormat.format("Student with id:{0} not found", id));
	}
  }
}
