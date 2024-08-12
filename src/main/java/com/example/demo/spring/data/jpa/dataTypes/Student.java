package com.example.demo.spring.data.jpa.dataTypes;

import static javax.persistence.CascadeType.PERSIST;

import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "student_id_seq", sequenceName = "student_id_seq", allocationSize = 100)
class Student {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_seq")
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
