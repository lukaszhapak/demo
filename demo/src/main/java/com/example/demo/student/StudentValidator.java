package com.example.demo.student;

import com.example.demo.commons.AbstractValidator;
import java.text.MessageFormat;
import org.springframework.stereotype.Component;

@Component
class StudentValidator extends AbstractValidator {

  void validate(Student student) {
	validateInteger(student.getAge(), 20, 100, "age");
	validateString(student.getName(), 2, 64, "Name");
  }
}
