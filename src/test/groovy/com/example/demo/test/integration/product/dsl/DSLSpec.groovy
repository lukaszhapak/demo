package com.example.demo.test.integration.product.dsl

import com.example.demo.common.httpClientTest.AbstractMockMvcIntegrationSpec
import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@AutoConfigureMockMvc
class DSLSpec extends AbstractMockMvcIntegrationSpec implements SampleProducts {

    def "should save product"() {
        when:
        Long id = saveProduct(sampleProduct).getId()

        then:
        assertProductsHaveSameFields(getProduct(id), sampleProduct)
    }

    Product saveProduct(Product product) {
        postHttpCall("/api/product", 200, product, Product.class)
    }

    Product getProduct(long id) {
        getHttpCall("/api/product/" + id, 200, Product.class)
    }

    void assertProductsHaveSameFields(Product first, Product second) {
        assert first.getName() == second.getName()
        assert first.getIntegerValue() == second.getIntegerValue()
    }

    void complexSave(Object object) {
        // register user
        // login
        // payment
        // some other required operations
        // save
    }
}
