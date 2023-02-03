package com.example.demo.modules.student.service;

import com.example.demo.commons.AbstractValidator;
import com.example.demo.modules.student.domain.Student;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
public class StudentValidator extends AbstractValidator {

  void validate(Student student) {
	invalidFields = new HashMap<>();
	validateInteger(student.getAge(), 20, 100, "Age");
	validateString(student.getName(), 2, 64, "Name");
	if (student.getGrades() != null && !student.getGrades().isEmpty()) {
	  for (Integer grade : student.getGrades()) {
		validateInteger(grade, 2, 5, "Grade");
	  }
	}
	throwException("Student");
  }
}
