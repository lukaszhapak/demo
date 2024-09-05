package com.example.demo.spring.modules.tools.outbox

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.entity.Student
import com.example.demo.spring.base.repository.StudentRepository
import org.springframework.beans.factory.annotation.Autowired

class OutboxSpec extends AbstractIntegrationSpec {

    @Autowired
    OutboxStudentService studentService
    @Autowired
    OutboxJob outboxJob
    @Autowired
    OutboxRepository outboxRepository
    @Autowired
    StudentRepository studentRepository

    def "should send message"() {
        given:
        studentService.save(getStudent())
        studentService.save(getStudent())
        studentService.save(getStudent())
        List<Outbox> messagesBeforeJob = outboxRepository.findAll()

        when:
        outboxJob.sendMessages()

        then:
        List<Outbox> messagesAfterJob = outboxRepository.findAll()
        messagesBeforeJob.stream().noneMatch { it.isSent() }
        messagesAfterJob.stream().allMatch { it.isSent() }
    }

    Student getStudent() {
        new Student().setFirstName("John").setAge(24)
    }
}
