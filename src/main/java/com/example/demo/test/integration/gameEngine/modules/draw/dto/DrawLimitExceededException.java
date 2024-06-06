package com.example.demo.test.integration.gameEngine.modules.draw.dto;

public class DrawLimitExceededException extends RuntimeException {

  public DrawLimitExceededException(String message) {
	super(message);
  }
}
