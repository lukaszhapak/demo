package com.example.demo.spring.http.server.rest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
class ResponseDTO {

  private ParamsDTO params;
  private String singleParam;
  private String singleHeader;
  private Student body;

}
