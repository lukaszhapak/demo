package com.example.demo.test.integration.product.io.data.overridingRepository;

import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.data.ProductRepository;
import java.util.List;
import java.util.Optional;

interface TestProductRepository extends ProductRepository {

  Optional<Product> findByQuantity(int quantity);

  Long count();

  List<Product> findAll();

}
