package com.example.demo.spring.context;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
class Stuudent {

  @Id
  @GeneratedValue
  Long id;
}
