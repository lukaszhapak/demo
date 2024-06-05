package com.example.demo.test.integration.product;

import com.example.demo.test.integration.product.data.Product;

public interface TestData {

  static Product getSampleProduct() {
	return new Product()
		.setName("Phone")
		.setQuantity(21);
  }
}
