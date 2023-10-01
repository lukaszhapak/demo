package com.example.demo.spring.jpql;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
class Parent {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @ManyToOne
  private Child child;

}
