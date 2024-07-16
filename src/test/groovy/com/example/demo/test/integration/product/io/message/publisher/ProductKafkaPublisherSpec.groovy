package com.example.demo.test.integration.product.io.message.publisher

import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@SpringBootTest
@ActiveProfiles("kafka-embedded")
@Import(SpecKafkaPublisherConfig)
@EmbeddedKafka(topics = "test-product-topic", partitions = 1)
class ProductKafkaPublisherSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    SpecKafkaListener specKafkaListener

    def "should receive message"() {
        given:
        PollingConditions pollingConditions = new PollingConditions(timeout: 2)

        when:
        productService.saveAndPublishEvent(sampleProduct)

        then:
        pollingConditions.eventually {
            specKafkaListener.receivedRecords.size() == 1
            // additional assertions to verify exact values
        }
    }
}
