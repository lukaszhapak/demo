package com.example.demo.spring.http.server.rest;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class HeadersDTO {

  private String name;
  private int age;
  private List<Integer> ids;

}
