package com.example.demo.nonspring.objectMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ObjectMapperTest {

  private final Service service = new Service(new ObjectMapper().registerModule(new JavaTimeModule()));

  @Test
  @DisplayName("should get student")
  void shouldGetStudent() {
	// given

	// when
	Student studentFromJson = service.getStudentAsObject();

	// then
  }
}