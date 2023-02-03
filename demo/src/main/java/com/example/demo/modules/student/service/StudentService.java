package com.example.demo.modules.student.service;

import static com.example.demo.commons.helper.PageRequestHelper.getPageRequest;

import com.example.demo.exception.NotFoundException;
import com.example.demo.modules.student.domain.Student;
import com.example.demo.modules.student.domain.StudentSearchSpecification;
import com.example.demo.modules.student.repository.StudentEntity;
import com.example.demo.modules.student.repository.StudentRepository;
import java.text.MessageFormat;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class StudentService {

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
	StudentEntity updateStudent = studentRepository.findStudentById(id).orElseThrow(() -> new NotFoundException(
		MessageFormat.format("Student with id:{0} not found", id)));
	updateStudent.updateFields(student);
	return updateStudent.toDomain();
  }

  public Page<Student> findAll(StudentSearchSpecification studentSearchSpecification) {
	return new PageImpl<>(studentRepository.findAll(studentSearchSpecification, getPageRequest(studentSearchSpecification.getPage(),
			studentSearchSpecification.getSize(), studentSearchSpecification.getSortBy(), studentSearchSpecification.getSortAscending()))
		.stream()
		.map(StudentEntity::toDomain)
		.collect(Collectors.toList()));
  }
}
