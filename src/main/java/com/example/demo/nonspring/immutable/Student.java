package com.example.demo.nonspring.immutable;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class Student {

  private String name;

  // getter for list should return immutable list or copy of that list

}
