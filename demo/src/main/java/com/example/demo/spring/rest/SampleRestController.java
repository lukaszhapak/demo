package com.example.demo.spring.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
class SampleRestController {

  @GetMapping("/get")
  public String get(Params params, @RequestHeader("player-id") String playerId) {
	log.debug("Getting with params={}, playerId={}", params, playerId);
	return params.toString();
  }
}
