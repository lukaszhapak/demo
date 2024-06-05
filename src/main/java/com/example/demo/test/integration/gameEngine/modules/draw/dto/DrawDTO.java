package com.example.demo.test.integration.gameEngine.modules.draw.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DrawDTO {

  Integer productId;
  Integer drawNumber;
  DrawStatus status;
}
