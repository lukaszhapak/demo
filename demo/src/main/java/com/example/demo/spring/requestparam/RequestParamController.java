package com.example.demo.spring.requestparam;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
class RequestParamController {

  @GetMapping("/params")
  public void get(Params params) {
    log.debug("Getting with params={}", params);
  }
}
