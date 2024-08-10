package com.example.demo.spring.message.kafka

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import spock.util.concurrent.PollingConditions

@EmbeddedKafka(topics = "test-topic", partitions = 1)
@ActiveProfiles("kafka-embedded")
class KafkaListenerSpec extends AbstractIntegrationSpec {

    @Autowired
    KafkaTemplate<String, KafkaEvent> kafkaTemplate
    @Autowired
    StudentRepository studentRepository

    def "should receive message"() {
        given:
        PollingConditions pollingConditions = new PollingConditions(timeout: 2)
        KafkaEvent event = new KafkaEvent("Test name 123")

        when:
        kafkaTemplate.send("test-topic", event)

        then:
        pollingConditions.eventually {
            studentRepository.existsByName(event.getBody())
        }
    }

    def "should get number of partitions"() {
        expect:
        kafkaTemplate.partitionsFor("test-topic").size() == 1
    }
}
