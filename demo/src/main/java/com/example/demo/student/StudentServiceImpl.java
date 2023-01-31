package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final StudentValidator studentValidator;

  @Override
  public Student getStudentById(Long id) {
	return Student.of(studentRepository.findStudentById(id).orElseThrow(RuntimeException::new));
  }

  @Override
  public Student saveStudent(Student student) {
	studentValidator.validate(student);
	StudentEntity studentEntity = StudentEntity.of(student);
	return Student.of(studentRepository.save(studentEntity));
  }
}
