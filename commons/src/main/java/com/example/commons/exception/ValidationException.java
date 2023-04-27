package com.example.commons.exception;

import java.text.MessageFormat;
import java.util.Map;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Getter
public class ValidationException extends RuntimeException {

  private final Map<String, String> invalidFields;

  public ValidationException(String message, Map<String, String> invalidFields) {
	super(message);
	this.invalidFields = invalidFields;
	log.error(MessageFormat.format("{0} invalid fields = {1}", message, invalidFields));
  }
}
