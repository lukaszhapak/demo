package com.example.demo.spring.modules.http.client.httpClient;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class HttpClientService {

  private final RestAssuredHttpClient restAssuredHttpClient;
  private final RestTemplateHttpClient restTemplateHttpClient;
  private final FeignHttpClient feignHttpClient;

  HttpResponseDTO getResponse() {
	return new HttpResponseDTO()
		.setValueFromRestAssured(restAssuredHttpClient.getValue())
		.setValueFromRestTemplate(restTemplateHttpClient.getValue())
		.setValueFromFeign(feignHttpClient.getValue().getValue());
  }
}
