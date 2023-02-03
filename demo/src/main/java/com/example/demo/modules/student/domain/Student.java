package com.example.demo.modules.student.domain;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Student {

  private Long id;
  private String name;
  private Integer age;
  private List<Integer> grades;
}
