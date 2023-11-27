package com.example.demo.spring.requestparam;

import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
class Params {

  private String name;
  private String surname;
  private int age;
  private List<Integer> ids;

}
