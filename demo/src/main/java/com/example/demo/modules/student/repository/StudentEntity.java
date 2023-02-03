package com.example.demo.modules.student.repository;

import com.example.demo.commons.AbstractEntity;
import com.example.demo.modules.student.domain.Student;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STUDENT")
public class StudentEntity extends AbstractEntity {

  private String name;
  private Integer age;
  @ElementCollection
  private List<Integer> grades;

  public void updateFields(Student student) {
	name = student.getName();
	age = student.getAge();
	grades = student.getGrades();
  }

  public static StudentEntity of(Student student) {
	StudentEntity studentEntity = new StudentEntity();
	studentEntity.setName(student.getName());
	studentEntity.setAge(student.getAge());
	studentEntity.setGrades(student.getGrades());
	return studentEntity;
  }

  public Student toDomain() {
	Student student = new Student();
	student.setId(getId());
	student.setName(getName());
	student.setAge(getAge());
	student.setGrades(getGrades());
	return student;
  }
}
