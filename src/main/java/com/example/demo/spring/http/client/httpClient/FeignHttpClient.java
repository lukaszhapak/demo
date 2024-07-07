package com.example.demo.spring.http.client.httpClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name= "value", url = "${external.service.url}")
interface FeignHttpClient {

  @RequestMapping(method = RequestMethod.GET, value = "/api/feign")
  ValueResponse getValue();
}
