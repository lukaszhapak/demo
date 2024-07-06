package com.example.demo.spring.http.client.httpClient;

import com.example.demo.test.integration.product.http.client.ValueResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
class RestTemplateHttpClient {

  @Value("${external.service.url}")
  private final String url;
  private final RestTemplate restTemplate;

  public String getValue() {
	return getHttpCall(url + "/api/rest-template").getValue();
  }

  private ValueResponse getHttpCall(String url) {
	return restTemplate.getForEntity(url, ValueResponse.class).getBody();
  }
}
