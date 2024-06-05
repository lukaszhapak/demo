package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class Draw {

  Long id;
  Integer productId;
  Integer drawNumber;
  DrawStatus status;
}
