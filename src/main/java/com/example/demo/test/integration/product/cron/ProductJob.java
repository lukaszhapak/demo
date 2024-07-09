package com.example.demo.test.integration.product.cron;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ProductJob {

  private final ProductService productService;

  @Scheduled(cron = "${product.cron}")
  public void setCronValue() {
	List<Product> products = productService.getByCronValue(false);
	for (Product product : products) {
	  product.setCronValue(true);
	  productService.save(product);
	}
  }
}
