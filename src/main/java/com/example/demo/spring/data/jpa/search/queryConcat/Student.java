package com.example.demo.spring.data.jpa.search.queryConcat;

import java.time.LocalDateTime;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@ToString
class Student {

  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private int age;

  private LocalDateTime date;

  @Embedded
  private Address address;
}
