package com.example.demo.test.unit.testingApproach.behavior;

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
class Student {

  private Long id;
  private String name;
  private int age;
}
