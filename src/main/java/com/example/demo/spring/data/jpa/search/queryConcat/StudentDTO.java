package com.example.demo.spring.data.jpa.search.queryConcat;

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
  private String firstName;
  private String lastName;
  private int age;
}
