package com.example.demo.spring.core.aop

import com.example.demo.common.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class AopSpec extends IntegrationSpec {

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
