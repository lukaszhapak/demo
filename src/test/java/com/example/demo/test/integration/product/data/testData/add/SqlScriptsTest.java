package com.example.demo.test.integration.product.data.testData.add;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.test.integration.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("classpath:sql/insert-product.sql")
class SqlScriptsTest {

  @Autowired
  ProductService productService;

  @Test
  void shouldExecuteClassAnnotatedSqlFile() {
	assertThat(productService.findByName("Product1")).isNotNull();
  }

  @Test
  @Sql("classpath:sql/insert-additional-product.sql")
  void shouldExecuteMethodAnnotatedSqlFile() {
	assertThat(productService.findByName("Product2")).isNotNull();
  }
}