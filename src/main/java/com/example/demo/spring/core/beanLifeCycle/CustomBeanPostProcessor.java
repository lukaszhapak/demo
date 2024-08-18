package com.example.demo.spring.core.beanLifeCycle;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
class CustomBeanPostProcessor implements BeanPostProcessor {

  private final OrderService orderService;

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
	if (bean instanceof BeanLifeCycleService) {
	  System.out.println("bean post processor before initialization");
	  orderService.store("bean post processor before initialization");
	}
	return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	if (bean instanceof BeanLifeCycleService) {
	  System.out.println("bean post processor after initialization");
	  orderService.store("bean post processor after initialization");
	}
	return bean;
  }
}
