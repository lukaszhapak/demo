package com.example.demo.spring.modules.http.client.httpClient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class HttpResponseDTO {

  private String valueFromRestAssured;
  private String valueFromRestTemplate;
  private String valueFromFeign;
}
