package com.example.demo.spring.modules.message.kafka

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.entity.Student
import com.example.demo.spring.base.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.context.EmbeddedKafka
import org.springframework.test.context.ActiveProfiles
import spock.util.concurrent.PollingConditions

@EmbeddedKafka(topics = "demo-student-updated-topic", partitions = 1)
@ActiveProfiles("kafka-embedded")
class KafkaListenerSpec extends AbstractIntegrationSpec {

    @Autowired
    KafkaTemplate<String, KafkaStudentUpdatedEvent> kafkaTemplate
    @Autowired
    StudentService studentService;

    def "should receive message"() {
        given:
        PollingConditions pollingConditions = new PollingConditions(timeout: 2)
        Long id = studentService.save(new Student().setFirstName("kafka listener spec")).getId()
        KafkaStudentUpdatedEvent kafkaStudentUpdatedEvent = new KafkaStudentUpdatedEvent().setId(id).setFirstName("test value")

        when:
        kafkaTemplate.send("demo-student-updated-topic", kafkaStudentUpdatedEvent)

        then:
        pollingConditions.eventually {
            studentService.findById(id).firstName == "test value"
        }
    }

    def "should get number of partitions"() {
        expect:
        kafkaTemplate.partitionsFor("demo-student-updated-topic").size() == 1
    }
}
