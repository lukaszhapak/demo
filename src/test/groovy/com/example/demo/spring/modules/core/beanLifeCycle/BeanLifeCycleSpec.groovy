package com.example.demo.spring.modules.core.beanLifeCycle

import com.example.demo.commons.AbstractIntegrationSpec
import com.example.demo.spring.modules.core.beanLifeCycle.OrderService
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
