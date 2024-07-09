package com.example.demo.test.integration.product.http.server;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @PostMapping("/api/product")
  public Product save(@RequestBody Product product) {
 	return productService.save(product);
  }

  @GetMapping("/api/product/{id}")
  public Product getById(@PathVariable Long id) {
	return productService.getById(id);
  }
}
