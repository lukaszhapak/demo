package com.example.demo.rest.entry;


import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Entry {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  private Long id;
  private String data;
}
