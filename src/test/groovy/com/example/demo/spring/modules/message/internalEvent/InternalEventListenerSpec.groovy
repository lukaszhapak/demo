package com.example.demo.spring.modules.message.internalEvent

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.entity.Student
import com.example.demo.spring.base.service.StudentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher

class InternalEventListenerSpec extends AbstractIntegrationSpec {

    @Autowired
    ApplicationEventPublisher applicationEventPublisher

    @Autowired
    StudentService studentService

    def "should receive event"() {
        given:
        Long id = studentService.save(new Student().setFirstName("kafka listener spec")).getId()
        InternalStudentUpdatedEvent internalStudentUpdatedEvent = new InternalStudentUpdatedEvent().setId(id).setFirstName("test value 3213")

        when:
        applicationEventPublisher.publishEvent(internalStudentUpdatedEvent)

        then:
        studentService.findById(id).firstName == "test value 3213"
    }
}