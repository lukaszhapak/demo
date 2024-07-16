package com.example.demo.test.integration.product

import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.data.ProductSearchCriteria

trait SampleProducts {

    // some base product with defaults values in required fields can be created

    Product sampleProduct = new Product()
            .setName("Phone")
            .setIntegerValue(21)

    Product invalidProduct = new Product()
            .setName("Phone")
            .setIntegerValue(123)

    Product firstProduct = new Product()
            .setName("First")
            .setIntegerValue(12)

    Product secondProduct = new Product()
            .setName("Second")
            .setIntegerValue(22)

    Product thirdProduct = new Product()
            .setName("Third")
            .setIntegerValue(32)

    ProductSearchCriteria searchCriteria = new ProductSearchCriteria()
            .setPage(0)
            .setSize(10)
            .setSortBy("id")
            .setSortAscending(true)

    ProductSearchCriteria getSearchCriteria() {
        new ProductSearchCriteria()
                .setPage(0)
                .setSize(10)
                .setSortBy("id")
                .setSortAscending(true)
    }
}