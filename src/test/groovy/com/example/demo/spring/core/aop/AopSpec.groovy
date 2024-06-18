package com.example.demo.spring.core.aop

import com.example.demo.common.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class AopSpec extends AbstractIntegrationSpec {

    @Autowired
    StudentService studentService

    def "should execute cacheable aspect methods"() {
        expect:
        studentService.getStudentCacheableAspect(1)
        studentService.getStudentCacheableAspect(2)
        studentService.getStudentCacheableAspect(2)
        studentService.getStudentCacheableAspect(3)
    }

    def "should execute logging aspect methods"() {
        expect:
        studentService.getStudentLoggingAspect(1)
    }
}
