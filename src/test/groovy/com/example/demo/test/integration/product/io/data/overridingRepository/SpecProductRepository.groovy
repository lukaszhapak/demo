package com.example.demo.test.integration.product.io.data.overridingRepository

import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.data.ProductRepository

interface SpecProductRepository extends ProductRepository {

    Optional<Product> findByQuantity(int quantity)

    Long count()

    List<Product> findAll()
}
