package com.example.demo.modules.student;

import com.example.demo.commons.AbstractEntity;
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

  public static StudentEntity of(Student student) {
	StudentEntity studentEntity = new StudentEntity();
	studentEntity.setId(student.getId());
	studentEntity.setName(student.getName());
	studentEntity.setAge(student.getAge());
	return studentEntity;
  }
}
