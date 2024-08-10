package com.example.demo.spring.core.beanLifeCycle

import com.example.demo.commons.AbstractIntegrationSpec
import org.springframework.beans.factory.annotation.Autowired

class BeanLifeCycleSpec extends AbstractIntegrationSpec {

    @Autowired
    OrderService orderService

    def "should start context"() {
        expect:
        orderService.getList() == ["constructor", "bean post processor before initialization", "post construct annotation",
                                   "after properties set from initializing bean", "init method",
                                   "bean post processor after initialization", "application runner", "command line runner"]
    }
}
