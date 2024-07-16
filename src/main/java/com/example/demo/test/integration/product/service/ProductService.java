package com.example.demo.test.integration.product.service;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import com.example.demo.test.integration.product.data.ProductSearchCriteria;
import com.example.demo.test.integration.product.data.ProductSearchSpecification;
import com.example.demo.test.integration.product.http.client.ProductHttpClient;
import com.example.demo.test.integration.product.message.publisher.ProductEventPublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

  private final ProductRepository productRepository;
  private final ProductHttpClient productHttpClient;
  private final ProductEventPublisher productEventPublisher;

  public Product save(Product product) {
	if (product.getIntegerValue() >= 50) {
	  throw new ValidationException("integer value too high");
	}
	return productRepository.save(product);
  }

  public Product saveAndPublishEvent(Product product) {
	// outbox pattern, saga choreography, 2 phase commit
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
	return productRepository.findById(id).orElse(null);
  }

  public Product getByName(String name) {
	return productRepository.findByName(name).orElse(null);
  }

  public List<Product> getProducts(ProductSearchCriteria productSearchCriteria) {
	Pageable pageRequest = PageRequest.of(productSearchCriteria.getPage(), productSearchCriteria.getSize(), Sort.by(productSearchCriteria.getSortBy()));
	return productRepository.findAll(new ProductSearchSpecification(productSearchCriteria), pageRequest).toList();
  }
}
