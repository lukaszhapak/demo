package com.example.demo.spring.core.cache

import com.example.demo.common.IntegrationSpec
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired

class CacheSpec extends IntegrationSpec {

    @Autowired
    StudentService studentService
    @Autowired
    UUIDController uuidController
    @SpringBean
    StudentRepository studentRepository = Mock()

    def "find one"() {
        when:
        studentService.findById(1L)
        studentService.save(new Student())
        studentService.findById(1L)
        studentService.update(1L, new Student())
        studentService.findById(1L)
        studentService.update(2L, new Student())
        studentService.findById(1L)
        studentService.deleteById(1L)
        studentService.findById(1L)
        studentService.deleteById(2L)
        studentService.findById(1L)

        then:
        3 * studentRepository.findById(_ as Long) >> Optional.of(new Student())
    }

    def "find all"() {
        when:
        studentService.findAll()
        studentService.findAll()
        studentService.save(new Student())
        studentService.findAll()
        studentService.findAll()
        studentService.update(1L, new Student())
        studentService.findAll()
        studentService.findAll()
        studentService.deleteById(1L)
        studentService.findAll()
        studentService.findAll()

        then:
        4 * studentRepository.findAll() >> new ArrayList<>()
    }

    def "get uuid"() {
        expect:
        uuidController.getCached()
        uuidController.getCached()
        uuidController.evictCached()
        uuidController.getCached()
        uuidController.getCached()
    }
}
