package com.example.demo.spring.core.beanLifeCycle;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class CustomBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
	if (bean instanceof Service) {
	  System.out.println("bean post processor before initialization");
	}
	return bean;
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	if (bean instanceof Service) {
	  System.out.println("bean post processor after initialization");
	}
	return bean;
  }
}
