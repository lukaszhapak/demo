package com.example.demo.nonspring.objectmapper;

import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ObjectMapperTest {

  private final Service service = new Service(new ObjectMapper());

  @Test
  @DisplayName("should get student")
  void shouldGetStudent() {
	// given

	// when
	Student studentFromJson = service.getStudentFromJson();

	// then
  }
}