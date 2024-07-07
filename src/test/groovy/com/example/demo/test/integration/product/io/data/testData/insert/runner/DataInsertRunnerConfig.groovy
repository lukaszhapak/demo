package com.example.demo.test.integration.product.io.data.testData.insert.runner


import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@TestConfiguration
class DataInsertRunnerConfig {

    @Bean
    static DataInsertRunner dataInsertRunner() {
        new DataInsertRunner()
    }
}
