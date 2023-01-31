package com.example.demo.modules.student;

import static com.example.demo.commons.HelperClass.stringAsCollection;

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

  public static Student of(StudentEntity studentEntity) {
	Student student = new Student();
	student.setId(studentEntity.getId());
	student.setName(studentEntity.getName());
	student.setAge(studentEntity.getAge());
	student.setGrades(stringAsCollection(studentEntity.getGrades()));
	return student;
  }
}
