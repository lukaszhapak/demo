package com.example.demo.student;

import java.util.Objects;

public interface StudentControllerTest {

  default boolean studentsAreEqual(Student student1, Student student2) {
	if (!Objects.equals(student1.getId(), student2.getId())) {
	  return false;
	}
	if (!Objects.equals(student1.getName(), student2.getName())) {
	  return false;
	}
	return Objects.equals(student1.getAge(), student2.getAge());
  }

  default Student getStudent(){
	Student student = new Student();
	student.setId(100L);
	student.setName("John");
	student.setAge(22);
	return student;
  }
}
