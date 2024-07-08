package com.example.demo.test.integration.product;

import com.example.demo.test.integration.product.data.Product;

public interface TestData {

  static Product getSampleProduct() {
	return new Product()
		.setName("Phone")
		.setIntegerValue(21);
  }

  static Product getInvalidProduct() {
	return new Product()
		.setName("Phone")
		.setIntegerValue(123);
  }
}
