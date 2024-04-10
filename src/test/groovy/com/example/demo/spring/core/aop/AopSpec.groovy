package com.example.demo.spring.core.aop

import com.example.demo.common.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class AopSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentService studentService;

    def "should execute aspect methods"() {
        expect:
        studentService.getStudent(1)
        studentService.getStudent(2)
        studentService.getStudent(2)
        studentService.getStudent(3)
    }
}
