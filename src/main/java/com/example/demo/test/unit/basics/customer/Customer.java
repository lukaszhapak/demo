package com.example.demo.test.unit.basics.customer;

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
  private boolean booleanValue;
  private int age;

  public Customer(String name, int age) {
	this.name = name;
	this.age = age;
	booleanValue = false;
  }

  public Customer(Long id, String name, int age) {
	this.id = id;
	this.name = name;
	this.age = age;
	booleanValue = false;
  }
}
