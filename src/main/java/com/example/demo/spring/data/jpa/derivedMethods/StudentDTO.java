package com.example.demo.spring.data.jpa.derivedMethods;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
class StudentDTO {

  private Long id;
  private String name;
  private int age;
}
