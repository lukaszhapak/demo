package com.example.demo.nonspring.util.objectMapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Service {

  private final ObjectMapper objectMapper;

  Student getStudentAsObject() {
	try {
	  return objectMapper.readValue(getStudentAsJson(), Student.class);
	} catch (JsonProcessingException e) {
	  throw new RuntimeException(e);
	}
  }

  String getStudentAsString() {
	String student;
	try {
	  student = objectMapper.writeValueAsString(getStudentAsObject());
	} catch (JsonProcessingException e) {
	  throw new RuntimeException(e);
	}
	return student;
  }

  private String getStudentAsJson() {
	return "{"
		+ "\"name\": \"John\","
		+ "\"age\": \"25\""
		+ "}";
  }
}
