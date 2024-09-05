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
	HttpResponseDTO httpResponseDTO = new HttpResponseDTO();
	httpResponseDTO.setValueFromRestAssured(restAssuredHttpClient.getValue());
	httpResponseDTO.setValueFromRestTemplate(restTemplateHttpClient.getValue());
	httpResponseDTO.setValueFromFeign(feignHttpClient.getValue().getValue());
	return httpResponseDTO;
  }
}
