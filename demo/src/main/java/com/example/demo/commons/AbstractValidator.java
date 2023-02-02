package com.example.demo.commons;

import java.text.MessageFormat;
import java.util.Map;

public class AbstractValidator {

  protected Map<String, String> invalidFields;

  protected void validateInteger(Integer integer, Integer min, Integer max, String name) {
	if (integer == null) {
	  invalidFields.put(name, "cannot be null");
	} else if (integer < min || integer > max) {
	  invalidFields.put(name,
		  MessageFormat.format("cannot be less than {0} and more than {1}", min, max));
	}
  }

  protected void validateString(String string, Integer min, Integer max, String name) {
	if (string == null) {
	  invalidFields.put(name, "cannot be null");
	} else if (string.length() < min || string.length() > max) {
	  invalidFields.put(name, MessageFormat.format(
		  "length cannot be less than {0} characters and more than {1} characters", min, max));
	}
  }
}
