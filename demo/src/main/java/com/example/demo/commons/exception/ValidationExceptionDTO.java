package com.example.demo.commons.exception;

import java.util.Map;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ValidationExceptionDTO {

 private final String message;
 private final Map<String, String> invalidFields;
}
