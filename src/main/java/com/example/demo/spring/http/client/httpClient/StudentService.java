package com.example.demo.spring.http.client.httpClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;
  private final RestAssuredHttpClient restAssuredHttpClient;
  private final RestTemplateHttpClient restTemplateHttpClient;

  Student save(Student student) {
	student.setValueFromRestAssured(restAssuredHttpClient.getValue());
	student.setValueFromRestTemplate(restTemplateHttpClient.getValue());
	return studentRepository.save(student);
  }
}
