package com.example.demo.test.integration.product.data;

import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

public interface ProductRepository extends Repository<Product, Long> {

  Product save(Product product);

  Optional<Product> findById(Long id);

  Optional<Product> findByName(String name);

  List<Product> findByCronValue(boolean cronValue);

  void deleteAll();

  Page<Product> findAll(Specification<Product> specification, Pageable pageable);
}
