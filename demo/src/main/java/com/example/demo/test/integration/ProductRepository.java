package com.example.demo.test.integration;

import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {

  Product save(Product product);

  Optional<Product> findById(Long id);

}
