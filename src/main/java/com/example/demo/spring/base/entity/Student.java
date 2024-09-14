package com.example.demo.spring.base.entity;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Setter
@Entity
@ToString
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
	@Index(name = "idx_student_age", columnList = "age"),
	@Index(name = "idx_student_first_name_last_name", columnList = "firstName, lastName", unique = false)
})
public class Student {

  @Id
  @GeneratedValue(
	  strategy = GenerationType.SEQUENCE,
	  generator = "student_id_seq"
  )
  @SequenceGenerator(
	  name = "student_id_seq",
	  sequenceName = "student_id_seq",
	  allocationSize = 100
  )
  private Long id;
  private String firstName;
  private String lastName;
  private int age;
  private LocalDateTime date;

  @Embedded
  private Address address;

  private Integer[] gradesArray;
}
