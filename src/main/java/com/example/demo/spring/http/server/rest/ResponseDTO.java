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

  private String singleParam;
  private ParamsDTO params;
  private String singleHeader;
  private HeadersDTO headers;
  private Student body;
  private String pathVariable;

}
