package com.example.demo.test.integration.product.service;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;

  public Product save(Product product) {
	return productRepository.save(product);
  }

  public List<Product> getByCronValue(boolean cronValue) {
	return productRepository.findByCronValue(cronValue);
  }
}
