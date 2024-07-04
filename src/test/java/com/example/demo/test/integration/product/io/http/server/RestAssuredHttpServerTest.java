package com.example.demo.test.integration.product.io.http.server;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.demo.commons.AbstractRestAssuredIntegrationTest;
import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.data.Product;
import org.junit.jupiter.api.Test;

class RestAssuredHttpServerTest extends AbstractRestAssuredIntegrationTest {

  @Test
  void shouldPostProduct() {
	// given
	Product request = TestData.getSampleProduct();

	// when
	Product response = postHttpCall("/api/product", request, Product.class, 200);

	// then
	assertThat(getHttpCall("/api/product/" + response.getId(), Product.class, 200).getName()).isEqualTo(request.getName());
  }
}
