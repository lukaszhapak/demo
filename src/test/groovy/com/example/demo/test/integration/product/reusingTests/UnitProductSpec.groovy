package com.example.demo.test.integration.product.reusingTests


import com.example.demo.test.integration.product.data.Product
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UnitProductSpec extends AbstractAdvancedProductSpec {

    // lets pretend this is unit test

    @Autowired
    ProductService productService

    @Override
    Product saveProduct(Product product) {
        productService.save(product)
    }

    @Override
    Product getProduct(long id) {
        productService.getById(id)
    }
}
