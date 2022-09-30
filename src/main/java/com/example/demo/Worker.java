package com.example.demo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
class Worker {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String firstName;
  private String lastName;
  private int age;
}
