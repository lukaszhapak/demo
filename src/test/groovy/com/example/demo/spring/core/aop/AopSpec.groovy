package com.example.demo.spring.core.aop

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class AopSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentService studentService

    def "should execute cacheable aspect methods"() {
        expect:
        studentService.get(1)
        studentService.get(2)
        studentService.get(2)
        studentService.get(3)
    }

    def "should execute logging aspect methods"() {
        expect:
        studentService.save(new Student().setId(21).setName("Jim"))
    }
}
