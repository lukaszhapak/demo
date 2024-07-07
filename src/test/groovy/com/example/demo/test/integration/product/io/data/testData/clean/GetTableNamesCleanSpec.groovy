package com.example.demo.test.integration.product.io.data.testData.clean

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.jdbc.core.JdbcOperations
import spock.lang.Specification

import java.util.stream.Collectors

@SpringBootTest
class GetTableNamesCleanSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    JdbcOperations jdbcOperations

    void cleanup() {
        cleanTables()
    }

    def "should save first product"() {
        expect:
        productService.getByName("TestName#7") == null
        productService.save(sampleProduct.setName("TestName#7"))
    }

    def "should save second product"() {
        expect:
        productService.getByName("TestName#7") == null
        productService.save(sampleProduct.setName("TestName#7"))
    }

    def "should save third product"() {
        expect:
        productService.getByName("TestName#7") == null
        productService.save(sampleProduct.setName("TestName#7"))
    }

    void cleanTables() {
        for (String table : fetchTables()) {
            jdbcOperations.execute("delete from " + table + ";")
        }
    }

    List<String> fetchTables() {
        jdbcOperations.queryForList("show tables")
                .stream()
                .map(Map::values)
                .map(x -> x.stream().findFirst().get())
                .collect(Collectors.toList())
    }
}
