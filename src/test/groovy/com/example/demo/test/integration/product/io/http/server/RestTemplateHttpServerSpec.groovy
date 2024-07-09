package com.example.demo.test.integration.product.io.http.server


import com.example.demo.common.httpClientTest.AbstractRestTemplateIntegrationSpec
import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.data.Product

class RestTemplateHttpServerSpec extends AbstractRestTemplateIntegrationSpec implements SampleProducts {

    def "should post product"() {
        when:
        Product response = postHttpCall("/api/product", 200, sampleProduct, Product.class)

        then:
        getHttpCall("/api/product/" + response.getId(), 200, Product.class).getName() == response.getName()
    }
}
