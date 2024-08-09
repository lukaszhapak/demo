package com.example.demo.nonspring.util.nullException

import spock.lang.Specification

class NullExceptionSpec extends Specification{

    def "should get exception"() {
        given:
        NullExceptionService exceptionService = new NullExceptionService();

        when:
        exceptionService.throwNullException()

        then:
        thrown NullPointerException
    }
}
