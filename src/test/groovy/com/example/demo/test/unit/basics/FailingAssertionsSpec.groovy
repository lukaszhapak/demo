package com.example.demo.test.unit.basics

import com.example.demo.test.unit.basics.customer.CustomerDTO
import spock.lang.Specification

class FailingAssertionsSpec extends Specification {

    CustomerDTO customerDTO = new CustomerDTO("John", 24)
    List<Integer> list = [1, 2, 3, 4]
//
//    def "should assert list size value"() {
//        expect:
//        list.size() == 2
//    }
//
//    def "should assert boolean value"() {
//        expect:
//        customerDTO.isBooleanValue()
//    }
//
//    def "should assert name"() {
//        expect:
//        customerDTO.getName() == "Michael"
//    }
//
//    def "should assert stream"() {
//        expect:
//        list.stream().allMatch { number -> number > 2 }
//    }
}
