package com.example.demo.spring.context;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.ToString;

@Entity
@ToString
class Student {

  @Id
  @GeneratedValue
  Long id;
}
