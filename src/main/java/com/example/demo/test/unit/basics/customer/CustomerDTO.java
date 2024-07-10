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
public class CustomerDTO {

  private String name;
  private Integer age;
  private boolean booleanValue;

  public CustomerDTO(String name, Integer age) {
	this.name = name;
	this.age = age;
	booleanValue = false;
  }
}
