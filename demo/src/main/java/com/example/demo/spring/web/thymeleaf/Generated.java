package com.example.demo.spring.web.thymeleaf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
class Generated {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private Integer age;

}

