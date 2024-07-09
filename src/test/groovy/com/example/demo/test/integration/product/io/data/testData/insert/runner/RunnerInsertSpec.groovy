package com.example.demo.test.integration.product.io.data.testData.insert.runner

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import spock.lang.Specification

@SpringBootTest
@Import(DataInsertRunnerConfig.class)
class RunnerInsertSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    def "should get product"() {
        expect:
        productService.getByName(sampleProduct.getName()) != null

        // test created new instance of sample product so id is null
        sampleProduct.getId() == null
    }
}
