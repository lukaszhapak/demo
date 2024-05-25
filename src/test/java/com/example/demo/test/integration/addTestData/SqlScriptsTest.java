package com.example.demo.test.integration.addTestData;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.demo.test.integration.data.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql("classpath:sql/insert-product.sql")
class SqlScriptsTest {

  @Autowired
  ProductRepository productRepository;

  @Test
  void shouldExecuteClassAnnotatedSqlFile() {
	assertThat(productRepository.findByName("Product1")).isNotEmpty();
  }

  @Test
  @Sql("classpath:sql/insert-additional-product.sql")
  void shouldExecuteMethodAnnotatedSqlFile() {
	assertThat(productRepository.findByName("Product2")).isNotEmpty();
  }
}