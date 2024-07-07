package com.example.demo.spring.http.client.httpClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class StudentService {

  private final StudentRepository studentRepository;
  private final RestAssuredHttpClient restAssuredHttpClient;
  private final RestTemplateHttpClient restTemplateHttpClient;
  private final FeignHttpClient feignHttpClient;

  Student save(Student student) {
	student.setValueFromRestAssured(restAssuredHttpClient.getValue());
	student.setValueFromRestTemplate(restTemplateHttpClient.getValue());
	student.setValueFromFeign(feignHttpClient.getValue().getValue());
	return studentRepository.save(student);
  }
}
