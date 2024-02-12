package com.example.demo.spring.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@AllArgsConstructor
class ResponseDTO {

  private Params params;
  private Student student;
  private String playerId;

}
