package com.example.demo.test.integration;

import com.example.demo.test.integration.data.Product;

public interface TestData {

  static Product getSampleProduct() {
	return new Product()
		.setName("Phone")
		.setQuantity(21);
  }
}
