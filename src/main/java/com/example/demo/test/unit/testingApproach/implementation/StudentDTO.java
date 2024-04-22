package com.example.demo.test.unit.testingApproach.implementation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
class StudentDTO {

  public StudentDTO(String name, int age) {
    this.name = name;
    this.age = age;
  }

  private Long id;
  private String name;
  private int age;
}
