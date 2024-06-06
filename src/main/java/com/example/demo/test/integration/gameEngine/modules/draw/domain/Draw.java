package com.example.demo.test.integration.gameEngine.modules.draw.domain;

import static javax.persistence.EnumType.STRING;

import com.example.demo.test.integration.gameEngine.modules.draw.dto.DrawStatus;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
class Draw {

  @Id
  @GeneratedValue
  Long id;
  Integer productId;
  Integer drawNumber;
  @Enumerated(STRING)
  DrawStatus status;
}
