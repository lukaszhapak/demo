package com.example.demo.test.integration;

public interface TestData {

  static Product getSampleProduct() {
	return new Product()
		.setName("Phone")
		.setQuantity(21);
  }
}
