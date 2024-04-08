package com.example.demo.spring.core.properties

import com.example.demo.common.IntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class PropertiesSpec extends IntegrationSpec {

    @Autowired
    PropertiesService propertiesService

    def "should get values from properties"() {
        expect:
        propertiesService.textFromProperties == "John"
        propertiesService.listFromProperties == ["John", "Max"]
        propertiesService.mapFromProperties.get("name") == "John"
        propertiesService.mapFromProperties.get("age") == "25"
    }
}
