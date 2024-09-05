package com.example.demo.nonspring.stream;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
class Student {
  String name;
  List<Integer> grades;

}
