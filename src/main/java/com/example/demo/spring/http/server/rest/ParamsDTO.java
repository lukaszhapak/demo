package com.example.demo.spring.http.server.rest;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class ParamsDTO {

  private String name;
  private String surname;
  private int age;
  private List<Integer> ids;

}
