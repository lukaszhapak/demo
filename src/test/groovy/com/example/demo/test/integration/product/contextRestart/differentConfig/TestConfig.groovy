package com.example.demo.test.integration.product.contextRestart.differentConfig

import com.example.demo.test.integration.product.http.client.ProductHttpClient
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate

@TestConfiguration
class TestConfig {

    @Bean
    static ProductHttpClient productHttpClient() {
        return new ProductHttpClient("test-url", new RestTemplate())
    }
}
