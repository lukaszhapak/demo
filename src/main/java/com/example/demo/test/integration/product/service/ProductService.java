package com.example.demo.test.integration.product.service;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product save(Product product) {
	return productRepository.save(product);
  }
}
