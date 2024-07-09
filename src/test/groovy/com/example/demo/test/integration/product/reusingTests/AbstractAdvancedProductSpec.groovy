package com.example.demo.test.integration.product.reusingTests

import com.example.demo.test.integration.product.SampleProducts

abstract class AbstractAdvancedProductSpec extends AbstractProductSpec implements SampleProducts {

    def "should save product and verify its integer value"() {
        when:
        Long id = saveProduct(sampleProduct).getId()

        then:
        getProduct(id).getIntegerValue() == sampleProduct.getIntegerValue()
    }
}
