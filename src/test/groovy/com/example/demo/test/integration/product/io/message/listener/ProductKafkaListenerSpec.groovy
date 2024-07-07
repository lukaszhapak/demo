package com.example.demo.test.integration.product.io.message.listener


import com.example.demo.test.integration.product.SampleProducts
import com.example.demo.test.integration.product.message.listener.IncomingKafkaEvent
import com.example.demo.test.integration.product.service.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification
import spock.util.concurrent.PollingConditions

@ActiveProfiles("kafka-embedded")
@EmbeddedKafka(topics = "test-product-topic", partitions = 1)
@SpringBootTest(properties = "product.listener.enabled=true")
class ProductKafkaListenerSpec extends Specification implements SampleProducts {

    @Autowired
    ProductService productService

    @Autowired
    KafkaTemplate<String, IncomingKafkaEvent> kafkaTemplate

    def "should receive message"() {
        given:
        Long id = productService.save(sampleProduct).getId()
        PollingConditions pollingConditions = new PollingConditions(timeout: 2)
        IncomingKafkaEvent event = new IncomingKafkaEvent().setProductId(id).setValue("value from kafka")

        when:
        kafkaTemplate.send("test-product-topic", event)

        then:
        pollingConditions.eventually {
            productService.getById(id).getKafkaValue() == "value from kafka"
        }
    }
}
