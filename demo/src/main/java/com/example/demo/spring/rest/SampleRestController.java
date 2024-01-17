package com.example.demo.spring.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
class SampleRestController {

  @PostMapping("/post")
  public String post(Params params, @RequestHeader("player-id") String playerId, @RequestBody Student student) {
	log.debug("Posting with params={}, playerId={}, student={}", params, playerId, student);
	return "todo";
  }
}
