package com.example.demo.test.integration.product.io.data.overridingRepository;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import java.util.List;
import java.util.Optional;

interface TestProductRepository extends ProductRepository {

  Optional<Product> findByIntegerValue(int integerValue);

  Long count();

  List<Product> findAll();

}
