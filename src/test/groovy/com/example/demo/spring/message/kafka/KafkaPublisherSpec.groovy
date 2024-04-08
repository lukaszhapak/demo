package com.example.demo.spring.message.kafka

import com.example.demo.common.IntegrationSpec
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import spock.util.concurrent.PollingConditions

@EmbeddedKafka(topics = "test-topic")
@ActiveProfiles("kafka-embedded")
@Import(KafkaTestConfig)
class KafkaPublisherSpec extends IntegrationSpec {

    @Autowired
    KafkaEventPublisher kafkaEventPublisher
    @Autowired
    KafkaTestListener testListener

    def "should send message"() {
        given:
        PollingConditions conditions = new PollingConditions(timeout: 2)

        when:
        kafkaEventPublisher.publishEvent()

        then:
        conditions.eventually {
            testListener.receivedRecords.size() == 1
        }
    }

    @TestConfiguration
    static class KafkaTestConfig {

        @Bean
        static KafkaTestListener testListener() {
            new KafkaTestListener()
        }
    }

    static class KafkaTestListener {

        List<KafkaEvent> receivedRecords = new LinkedList<>()

        @KafkaListener(id = "test-demo-application", topics = "test-topic")
        void listen(ConsumerRecord<String, KafkaEvent> kafkaEvent) {
            receivedRecords.add(kafkaEvent.value())
        }
    }
}
