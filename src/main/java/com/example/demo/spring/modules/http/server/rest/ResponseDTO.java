package com.example.demo.spring.modules.http.server.rest;

import com.example.demo.spring.base.entity.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
class ResponseDTO {

  private String singleParam;
  private ParamsDTO params;
  private String singleHeader;
  private HeadersDTO headers;
  private Student body;
  private String pathVariable;

}
