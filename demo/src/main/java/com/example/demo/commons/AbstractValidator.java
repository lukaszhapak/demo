package com.example.demo.commons;

import com.example.demo.exception.ValidationException;
import java.text.MessageFormat;

public class AbstractValidator {

  protected void validateInteger(Integer integer, Integer min, Integer max, String name) {
	if (integer == null) {
	  throw new ValidationException(MessageFormat.format("{0} cannot be null", name));
	}
	if (integer < min) {
	  throw new ValidationException(
		  MessageFormat.format("{0} cannot be less than {1}", name, min));
	}
	if (integer > max) {
	  throw new ValidationException(
		  MessageFormat.format("{0} cannot less be more than {1}", name, max));
	}
  }

  protected void validateString(String string, Integer min, Integer max, String name) {
	if (string == null) {
	  throw new ValidationException(MessageFormat.format("{0} cannot be null", name));
	}
	if (string.length() < min) {
	  throw new ValidationException(
		  MessageFormat.format("{0} length cannot be less than {1} characters", name, min));
	}
	if (string.length() > max) {
	  throw new ValidationException(
		  MessageFormat.format("{0} length cannot be more than {1} characters", name, max));
	}
  }

}
