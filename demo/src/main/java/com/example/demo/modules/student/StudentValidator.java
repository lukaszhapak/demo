package com.example.demo.modules.student;

import com.example.demo.commons.AbstractValidator;
import com.example.demo.modules.student.api.Student;
import org.springframework.stereotype.Component;

@Component
class StudentValidator extends AbstractValidator {

  void validate(Student student) {
	validateInteger(student.getAge(), 20, 100, "age");
	validateString(student.getName(), 2, 64, "Name");
  }
}
