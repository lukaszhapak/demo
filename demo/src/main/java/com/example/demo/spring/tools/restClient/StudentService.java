package com.example.demo.spring.tools.restClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;
  private final StudentNameRestClient studentNameRestClient;

  Student save(Student student) {
	student.setName(studentNameRestClient.getName());
	return studentRepository.save(student);
  }
}
