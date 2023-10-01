package com.example.demo.spring.jpql;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
class Child {

  @Id
  @GeneratedValue
  private Long id;
  private String name;

  @OneToMany
  List<Parent> parents;
}
