package com.example.demo.student;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Student {

  private Long id;
  private String name;
  private Integer age;

  public static Student of(StudentEntity studentEntity) {
	Student student = new Student();
	student.setId(studentEntity.getId());
	student.setName(studentEntity.getName());
	student.setAge(studentEntity.getAge());
  	return student;
  }
}
