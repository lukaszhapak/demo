package com.example.demo.modules.student;

import com.example.demo.commons.AbstractValidator;
import com.example.demo.exception.ValidationException;
import com.example.demo.modules.student.api.Student;
import java.util.HashMap;
import org.springframework.stereotype.Component;

@Component
class StudentValidator extends AbstractValidator {

  void validate(Student student) {
	invalidFields = new HashMap<>();
	validateInteger(student.getAge(), 20, 100, "Age");
	validateString(student.getName(), 2, 64, "Name");
	if (student.getGrades() != null && !student.getGrades().isEmpty()) {
	  for (Integer grade : student.getGrades()) {
		validateInteger(grade, 2, 5, "Grade");
	  }
	}
	if (invalidFields.size() > 0) {
	  throw new ValidationException("Student is invalid", invalidFields);
	}
  }
}
