package com.example.demo.spring.testRestClients;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
class ResponseDTO {

  private ParamsDTO paramsDTO;
  private Student student;
  private String userId;

}
