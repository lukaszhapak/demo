package com.example.demo.spring.tools.outbox

import com.example.demo.common.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class OutboxSpec extends IntegrationSpec {

    @Autowired
    StudentService studentService
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
        List<Student> students = studentRepository.findAll()

        when:
        outboxJob.sendMessages()

        then:
        List<Outbox> messagesAfterJob = outboxRepository.findAll()
        messagesAfterJob.size() == students.size()
        messagesBeforeJob.stream().noneMatch { it.isSent() }
        messagesAfterJob.stream().allMatch { it.isSent() }
    }

    Student getStudent() {
        new Student().setName("John").setAge(24)
    }
}
