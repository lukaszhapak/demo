package com.example.demo.test.integration.product.io.data.testData.insert

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.jdbc.Sql
import spock.lang.Specification

@SpringBootTest
@Sql("classpath:sql/insert-product.sql")
class SqlScriptInsertSpec extends Specification implements SampleProducts {

    /// refactoring table will require changes in scripts

    @Autowired
    ProductService productService

    def "should execute class annotated sql file"() {
        expect:
        productService.getByName("Product1") != null
    }

    @Sql("classpath:sql/insert-additional-product.sql")
    def "should execute method annotated sql file"() {
        productService.getByName("Product2") != null
    }
}
