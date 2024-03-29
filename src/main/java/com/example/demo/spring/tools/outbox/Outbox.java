package com.example.demo.spring.tools.outbox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Outbox {

  @Id
  @GeneratedValue
  private Long id;
  private String body;
  private Class eventClass;
  private String destination;
  private boolean sent;

}
