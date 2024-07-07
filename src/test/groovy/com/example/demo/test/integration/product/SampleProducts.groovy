package com.example.demo.test.integration.product

import com.example.demo.test.integration.product.data.Product

trait SampleProducts {

    Product sampleProduct = new Product()
            .setName("Phone")
            .setQuantity(21)

    Product invalidProduct = new Product()
            .setName("Phone")
            .setQuantity(123)
}