package com.example.demo.test.integration.product.dsl

import com.example.demo.test.integration.product.data.Product

interface DslSpec {

    Product saveProduct(Product product)

    Product getProduct(long id)

    default void complexSave(Object object) {
        // register user
        // login
        // some other operations
        // save
    }
}
