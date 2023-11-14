package com.example.demo.spring.hibernate;

import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.CascadeType.PERSIST;

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

  @OneToOne(cascade = PERSIST)
  private StudentOneToOne oneToOne;

  @Embedded
  private Address address;

  private Integer[] gradesArray;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<Integer> gradesList;

}
