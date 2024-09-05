package com.example.demo.spring.modules.core.aop

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.base.entity.Student
import org.springframework.beans.factory.annotation.Autowired

class AopSpec extends AbstractIntegrationSpec {

    @Autowired
    AopStudentService studentService

    def "should execute cacheable aspect methods"() {
        expect:
        studentService.findById(1)
        studentService.findById(2)
        studentService.findById(2)
        studentService.findById(3)
    }

    def "should execute logging aspect methods"() {
        expect:
        studentService.save(new Student().setFirstName("John").setAge(25)).id != null
//        studentService.findByName("John").name == "John"
//        studentService.findByNameAndAge("John", 25).name == "John"

        studentService.publishEvent(new Student().setId(32).setFirstName("Michael"))
        studentService.noArgumentsMethod()
    }
}
