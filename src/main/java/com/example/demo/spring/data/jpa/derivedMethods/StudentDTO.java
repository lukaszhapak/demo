package com.example.demo.spring.data.jpa.derivedMethods;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
class StudentDTO {

  private String name;
  private int age;
}
