package com.example.demo.test.integration.product.http.client;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class ProductHttpClient {

  @Value("${product.externalService.url}")
  private final String url;
  private final RestTemplate restTemplate;

  public String getValue() {
	return getHttpCall(url + "/api/value").getValue();
  }

  private ValueResponse getHttpCall(String url) {
	return restTemplate.getForEntity(url, ValueResponse.class).getBody();
  }
}
