package com.example.demo.spring.http.server.thymeleaf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
class Student {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private Integer age;

}

