package com.example.demo.spring.modules.http.server.rest;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class ParamsDTO {

  private String name;
  private int age;
  private List<Integer> ids;

}
