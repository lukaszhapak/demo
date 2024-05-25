package com.example.demo.test.integration.httpserver;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {

  @GetMapping("/api/product/string")
  public String string() {
	return "Product controller response";
  }
}
