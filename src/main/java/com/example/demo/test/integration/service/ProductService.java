package com.example.demo.test.integration.service;

import com.example.demo.test.integration.data.Product;
import com.example.demo.test.integration.data.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product save(Product product) {
	return productRepository.save(product);
  }
}
