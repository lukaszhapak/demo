package com.example.demo.spring.hibernate;

import static javax.persistence.CascadeType.PERSIST;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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

  @OneToOne(cascade = PERSIST)
  private StudentOneToOne oneToOne;

  @OneToMany(cascade = PERSIST)
  @JoinColumn(name = "Student_id", nullable = false)
  private List<StudentOneToMany> oneToMany;

  @Embedded
  private Address address;

  private Integer[] gradesArray;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<Integer> gradesList;

}
