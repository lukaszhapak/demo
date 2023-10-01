package com.example.demo.spring.outbox;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
class Student {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private int age;
}
