package com.example.demo.nonspring.rest;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class Entry {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String data;
}
