package com.example.demo.test.integration.product.io.message.publisher

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class SpecKafkaPublisherConfig {

    @Bean
    static SpecKafkaListener specKafkaListener() {
        new SpecKafkaListener()
    }
}
