package com.example.demo.student;

import com.example.demo.commons.AbstractEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Student extends AbstractEntity {

  private String name;
  private Integer age;
}
