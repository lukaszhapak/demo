package com.example.demo.nonspring.assertJ;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
class Student {

  private Long id;
  private String name;
  private int age;
}
