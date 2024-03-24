package com.example.demo.spring.http.server.thymeleaf;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  List<Student> findAll() {
	return studentRepository.findAll();
  }

  Student findById(long id) {
	return studentRepository.findById(id).orElse(null);
  }

  void save(Student student) {
	studentRepository.save(student);
  }

  void deleteById(long id) {
	studentRepository.deleteById(id);
  }
}

