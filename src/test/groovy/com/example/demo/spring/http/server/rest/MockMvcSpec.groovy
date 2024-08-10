package com.example.demo.spring.http.server.rest

import com.example.demo.commons.httpClientTest.AbstractMockMvcIntegrationSpec

class MockMvcSpec extends AbstractMockMvcIntegrationSpec {

    def "should get with single param in url"() {
        expect:
        getHttpCall("/api/param/single?singleParam=42112", 200, ResponseDTO).getSingleParam() == "42112"
    }

    def "should get list of students"() {
        when:
        List<Student> list = getHttpCall("/api/student", 200, Student[])

        then:
        list.size() == 2
    }

    def "should post student"() {
        when:
        Student student = postHttpCall("/api/student", 200, new Student(2, "Jim", 42), Student)

        then:
        student.getId() == 2
    }

    def "should put student"() {
        when:
        Student student = putHttpCall("/api/student/2", 200, new Student(2, "Jim", 42), Student)

        then:
        student.getId() == 2
    }

    def "should delete student"() {
        expect:
        deleteHttpCall("/api/student/2", 200)
    }
}

