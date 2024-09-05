package com.example.demo.spring.modules.message.kafka

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.entity.Student
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
class KafkaPublisherSpec extends AbstractIntegrationSpec {

    @Autowired
    KafkaService kafkaService
    @Autowired
    KafkaTestListener testListener

    def "should send message"() {
        given:
        PollingConditions pollingConditions = new PollingConditions(timeout: 2)

        when:
        kafkaService.saveAndPublishEvent(new Student().setFirstName("test value"))

        then:
        pollingConditions.eventually {
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

        List<KafkaStudentRegisteredEvent> receivedRecords = new LinkedList<>()

        @KafkaListener(id = "test-demo-application", topics = "demo-student-registered-topic")
        void listen(ConsumerRecord<String, KafkaStudentRegisteredEvent> kafkaEvent) {
            receivedRecords.add(kafkaEvent.value())
        }
    }
}
