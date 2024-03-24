package com.example.demo.spring.http.client.restAssured;

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
