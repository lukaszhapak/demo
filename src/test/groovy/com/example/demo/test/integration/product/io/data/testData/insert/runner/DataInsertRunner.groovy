package com.example.demo.test.integration.product.io.data.testData.insert.runner

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner

class DataInsertRunner implements CommandLineRunner, SampleProducts {

    @Autowired
    private ProductRepository productRepository

    @Override
    void run(String... args) throws Exception {
        productRepository.save(sampleProduct)
    }
}
