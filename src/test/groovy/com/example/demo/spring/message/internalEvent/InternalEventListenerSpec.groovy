package com.example.demo.spring.message.internalEvent

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher

class InternalEventListenerSpec extends AbstractIntegrationSpec {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher

    @Autowired
    StudentRepository studentRepository

    def "should receive event"() {
        given:
        InternalEvent event = new InternalEvent("Test name 123")

        when:
        applicationEventPublisher.publishEvent(event)

        then:
        studentRepository.existsByName(event.getBody())
    }
}