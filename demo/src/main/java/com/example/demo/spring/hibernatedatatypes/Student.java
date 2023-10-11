package com.example.demo.spring.hibernatedatatypes;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Student {

  @Id
  @GeneratedValue
  private Long id;
  private String firstName;
  private String lastName;
  private int age;
  private int[] grades;
  @Embedded
  private Address address;
}
