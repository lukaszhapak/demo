package com.example.demo.spring.modules.core.properties

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.modules.core.properties.PropertiesService
import org.springframework.beans.factory.annotation.Autowired

class PropertiesSpec extends AbstractIntegrationSpec {

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
