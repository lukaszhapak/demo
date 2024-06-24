package com.example.demo.test.integration.product.http.server;

import com.example.demo.test.integration.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("/api/product/string")
  public String string() {
	return "Product controller response";
  }
}
