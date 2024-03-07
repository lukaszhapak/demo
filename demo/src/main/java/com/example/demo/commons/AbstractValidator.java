package com.example.demo.commons;

import com.example.demo.commons.exception.ValidationException;
import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public abstract class AbstractValidator {

  protected Map<String, String> invalidFields = new HashMap<>();

  protected void validateInteger(Integer integer, Integer min, Integer max, String name) {
	if (integer == null) {
	  invalidFields.put(name, "cannot be null");
	} else if (integer < min || integer > max) {
	  invalidFields.put(name, MessageFormat.format("cannot be less than {0} and more than {1}", min, max));
	}
  }

  protected void validateString(String string, Integer min, Integer max, String name) {
	if (string == null) {
	  invalidFields.put(name, "cannot be null");
	} else if (string.length() < min || string.length() > max) {
	  invalidFields.put(name, MessageFormat.format("length cannot be less than {0} characters and more than {1} characters", min, max));
	}
  }

  protected void validateFutureDate(LocalDateTime date, String name) {
	if (date == null) {
	  invalidFields.put(name, "cannot be null");
	} else if (date.isBefore(LocalDateTime.now())) {
	  invalidFields.put(name, "date have to be from future");
	}
  }

  protected void throwException(String objectName) {
	if (invalidFields.size() > 0) {
	  throw new ValidationException(objectName + " is invalid", invalidFields);
	}
  }
}