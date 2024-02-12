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
  public ResponseDTO post(Params params, @RequestHeader("player-id") String playerId, @RequestBody Student student) {
	log.debug("Request with params={}, playerId={}, student={}", params, playerId, student);
	return new ResponseDTO(params, student, playerId);
  }
}
