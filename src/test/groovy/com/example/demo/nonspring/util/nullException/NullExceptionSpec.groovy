package com.example.demo.nonspring.util.nullException

import spock.lang.Specification

class NullExceptionSpec extends Specification {

    NullExceptionService exceptionService = new NullExceptionService();

    def "should get exception"() {
        when:
        exceptionService.throwNullException()

        then:
        thrown NullPointerException
    }
}
