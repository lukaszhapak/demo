package com.example.demo.nonspring.test;

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
class Student {

  public Student(String name, int age) {
	this.name = name;
	this.age = age;
  }

  private Long id;
  private String name;
  private int age;
}
