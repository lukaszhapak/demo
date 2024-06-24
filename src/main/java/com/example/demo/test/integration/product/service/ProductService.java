package com.example.demo.test.integration.product.service;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import com.example.demo.test.integration.product.http.client.ProductHttpClient;
import com.example.demo.test.integration.product.message.publisher.ProductEventPublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductHttpClient productHttpClient;
  private final ProductEventPublisher productEventPublisher;

  public Product save(Product product) {
	return productRepository.save(product);
  }

  public Product saveAndPublishEvent(Product product) {
	productEventPublisher.publishProductAddedEvent(product);
	return productRepository.save(product);
  }

  public void assignValueFromExternalService(Long id) {
	Product product = getById(id);
	product.setClientValue(productHttpClient.getValue());
	productRepository.save(product);
  }

  public List<Product> getByCronValue(boolean cronValue) {
	return productRepository.findByCronValue(cronValue);
  }

  public Product getById(Long id) {
	return productRepository.findById(id).get();
  }
}
