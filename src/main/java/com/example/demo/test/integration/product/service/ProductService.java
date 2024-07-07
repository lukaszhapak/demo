package com.example.demo.test.integration.product.service;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import com.example.demo.test.integration.product.http.client.ProductHttpClient;
import com.example.demo.test.integration.product.message.publisher.ProductEventPublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductHttpClient productHttpClient;
  private final ProductEventPublisher productEventPublisher;

  public Product save(Product product) {
//	log.debug("Saving product={}", product);
	if (product.getQuantity() >= 50) {
	  throw new ValidationException("quantity too high");
	}
	return productRepository.save(product);
  }

  public Product saveAndPublishEvent(Product product) {
	productEventPublisher.publishProductAddedEvent(product);
	return productRepository.save(product);
  }

  public void assignValueFromExternalService(Long id) {
	Product product = findById(id);
	product.setClientValue(productHttpClient.getValue());
	productRepository.save(product);
  }

  public List<Product> getByCronValue(boolean cronValue) {
	return productRepository.findByCronValue(cronValue);
  }

  public Product findById(Long id) {
//	log.debug("Getting product with id={}", id);
	return productRepository.findById(id).orElse(null);
  }

  public Product findByName(String name) {
//	log.debug("Getting product with name={}", name);
	return productRepository.findByName(name).orElse(null);
  }
}
