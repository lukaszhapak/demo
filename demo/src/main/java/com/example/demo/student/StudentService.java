package com.example.demo.student;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;
  private final StudentValidator studentValidator;

  Student getStudentById(Long id) {
	return studentRepository.findStudentById(id);
  }

  Student saveStudent(Student student) {
	studentValidator.validate(student);
	return studentRepository.save(student);
  }
}
