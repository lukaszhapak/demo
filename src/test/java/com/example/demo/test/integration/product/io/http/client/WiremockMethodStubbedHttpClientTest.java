package com.example.demo.test.integration.product.io.http.client;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.service.ProductService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;

@AutoConfigureWireMock(port = 0)
@SpringBootTest(properties = {"product.externalService.url=http://localhost:${wiremock.server.port}"})
class WiremockMethodStubbedHttpClientTest {

  @Autowired
  ProductService productService;

  @Test
  @DisplayName("should get value from external service")
  void shouldGetValueFromExternalService() {
	// given
	stubExternalService(200, "{\"value\" : \"value-from-method\"}");
	Long id = productService.save(TestData.getSampleProduct()).getId();

	// when
	productService.assignValueFromExternalService(id);

	// then
	assertThat(productService.getById(id).getClientValue()).isEqualTo("value-from-method");
  }

  void stubExternalService(int status, String body) {
	stubFor(get(urlEqualTo("/api/value"))
		.willReturn(aResponse()
			.withStatus(status)
			.withBody(body)
			.withHeader("Content-Type", "application/json")
		));
  }
}
