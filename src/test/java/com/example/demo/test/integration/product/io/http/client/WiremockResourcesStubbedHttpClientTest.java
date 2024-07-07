package com.example.demo.test.integration.product.io.http.client;

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
class WiremockResourcesStubbedHttpClientTest {

  @Autowired
  ProductService productService;

  @Test
  @DisplayName("should get value from external service")
  void shouldGetValueFromExternalService() {
	// given
	Long id = productService.save(TestData.getSampleProduct()).getId();

	// when
	productService.assignValueFromExternalService(id);

	// then
	assertThat(productService.getById(id).getClientValue()).isEqualTo("value-from-resources");
  }
}
