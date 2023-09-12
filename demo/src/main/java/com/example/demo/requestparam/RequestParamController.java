package com.example.demo.requestparam;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class RequestParamController {

  @GetMapping("/params")
  public void get(Params params) {

  }

}
