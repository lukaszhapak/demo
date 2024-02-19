package com.example.demo.nonspring.test;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  Student save(Student student) {
	return studentRepository.save(student);
  }

  Student getStudentByName(String name) {
	return studentRepository.getStudentByName(name);
  }
  Student getStudentById(Long id) {
	return studentRepository.getStudentById(id);
  }

  Student getStudentByNameAndId(String name, Long id) {
	return studentRepository.getStudentByNameAndId(name, id);

  }
}
