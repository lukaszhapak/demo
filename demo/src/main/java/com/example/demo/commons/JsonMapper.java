package com.example.demo.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public interface JsonMapper {

  ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

  default String serialize(Object object) {
	String result;
	try {
	  result = objectMapper.writeValueAsString(object);
	} catch (JsonProcessingException e) {
	  throw new RuntimeException(e);
	}
	return result;
  }

  default Object deserialize(String string, Class clazz) {
	try {
	  return objectMapper.readValue(string, clazz);
	} catch (JsonProcessingException e) {
	  throw new RuntimeException(e);
	}
  }
}
