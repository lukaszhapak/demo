package com.example.demo.modules.student.repository;

import static com.example.demo.commons.helper.MappingHelper.collectionAsString;
import static com.example.demo.commons.helper.MappingHelper.stringAsCollection;

import com.example.demo.commons.AbstractEntity;
import com.example.demo.modules.student.domain.Student;
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
  private String grades;

  public void updateFields(Student student) {
	name = student.getName();
	age = student.getAge();
	grades = collectionAsString(student.getGrades());
  }

  public static StudentEntity of(Student student) {
	StudentEntity studentEntity = new StudentEntity();
	studentEntity.setName(student.getName());
	studentEntity.setAge(student.getAge());
	studentEntity.setGrades(collectionAsString(student.getGrades()));
	return studentEntity;
  }

  public Student toDomain() {
	Student student = new Student();
	student.setId(getId());
	student.setName(getName());
	student.setAge(getAge());
	student.setGrades(stringAsCollection(getGrades()));
	return student;
  }
}
