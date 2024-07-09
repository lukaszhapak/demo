package com.example.demo.test.integration.product.http.server;

import com.example.demo.test.integration.product.service.ValidationException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ErrorDTO {

  private String message;

  public ErrorDTO(ValidationException exception) {
	this.message = exception.getMessage();
  }
}
