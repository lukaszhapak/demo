package com.example.demo.test.integration;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product save(Product product) {
	return productRepository.save(product);
  }
}
