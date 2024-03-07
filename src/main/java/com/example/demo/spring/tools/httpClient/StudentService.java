package com.example.demo.spring.tools.httpClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;
  private final StudentNameHttpClient studentNameHttpClient;

  Student save(Student student) {
	student.setName(studentNameHttpClient.getName(student.getSource()));
	return studentRepository.save(student);
  }
}
