package com.example.demo.test.basics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

  private Long id;
  private String name;
  private int age;
  public Customer(String name, int age) {
	this.name = name;
	this.age = age;
  }
}
