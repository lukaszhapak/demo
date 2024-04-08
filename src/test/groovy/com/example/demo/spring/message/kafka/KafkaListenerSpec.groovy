package com.example.demo.spring.message.kafka

import com.example.demo.common.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import spock.util.concurrent.PollingConditions

@EmbeddedKafka(topics = "test-topic")
@ActiveProfiles("kafka-embedded")
class KafkaListenerSpec extends IntegrationSpec {

    @Autowired
    KafkaTemplate<String, KafkaEvent> kafkaTemplate
    @Autowired
    StudentRepository studentRepository

    def "should receive message"() {
        given:
        PollingConditions conditions = new PollingConditions(timeout: 2)
        KafkaEvent event = new KafkaEvent("Test name 123")

        when:
        kafkaTemplate.send("test-topic", event)

        then:
        conditions.eventually {
            studentRepository.existsByName(event.getBody())
        }
    }
}
