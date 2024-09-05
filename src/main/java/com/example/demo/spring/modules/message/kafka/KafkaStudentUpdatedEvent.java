package com.example.demo.spring.modules.message.kafka;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class KafkaStudentUpdatedEvent {

  private Long id;
  private String firstName;
}
