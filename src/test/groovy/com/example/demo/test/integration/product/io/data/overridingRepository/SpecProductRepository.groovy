package com.example.demo.test.integration.product.io.data.overridingRepository

import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.data.ProductRepository

interface SpecProductRepository extends ProductRepository {

    Optional<Product> findByIntegerValue(int integerValue)

    Long count()

    List<Product> findAll()
}
