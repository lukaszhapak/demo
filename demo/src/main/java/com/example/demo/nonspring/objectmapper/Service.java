package com.example.demo.nonspring.objectmapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class Service {
    private final ObjectMapper objectMapper;

	Student getStudentFromJson() {
	  try {
		return objectMapper.readValue(getStudentAsJson(), Student.class);
	  } catch (JsonProcessingException e) {
		throw new RuntimeException(e);
	  }
	}

	private String getStudentAsJson() {
	  return "{"
		  + "\"name\": \"John\","
		  + "\"age\": \"25\""
		  + "}";
	}
}
