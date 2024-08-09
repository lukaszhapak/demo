package com.example.demo.nonspring.util.objectMapper;

import com.example.demo.commons.JsonMapper;

class ObjectMapperService implements JsonMapper {

  String studentAsJson =
	  "{"
		  + "\"name\": \"John\","
		  + "\"age\": \"25\""
		  + "}";

  Student studentAsObject = new Student()
	  .setName("Jim")
	  .setAge(32);

  Student getStudentAsObject() {
	return deserialize(studentAsJson, Student.class);
  }

  String getStudentAsString() {
	return serialize(studentAsObject);
  }
}
