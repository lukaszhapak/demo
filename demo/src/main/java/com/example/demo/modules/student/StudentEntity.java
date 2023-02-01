package com.example.demo.modules.student;

import static com.example.demo.commons.HelperClass.collectionAsString;
import static com.example.demo.commons.HelperClass.stringAsCollection;

import com.example.demo.commons.AbstractEntity;
import com.example.demo.modules.student.api.Student;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "STUDENT")
class StudentEntity extends AbstractEntity {

  private String name;
  private Integer age;
  private String grades;

  static StudentEntity of(Student student) {
	StudentEntity studentEntity = new StudentEntity();
	studentEntity.setName(student.getName());
	studentEntity.setAge(student.getAge());
	studentEntity.setGrades(collectionAsString(student.getGrades()));
	return studentEntity;
  }

  Student toDomain() {
	Student student = new Student();
	student.setId(getId());
	student.setName(getName());
	student.setAge(getAge());
	student.setGrades(stringAsCollection(getGrades()));
	return student;
  }
}
