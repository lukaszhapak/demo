package com.example.demo.nonspring.mockito;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;

  Student save(Student student) {
	return studentRepository.save(student);
  }

}
