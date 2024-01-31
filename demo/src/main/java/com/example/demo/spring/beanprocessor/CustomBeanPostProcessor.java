package com.example.demo.spring.beanprocessor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Slf4j
@Component
class CustomBeanPostProcessor implements BeanPostProcessor {

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
	return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	if(beanName.contains("spring.beanprocessor")) {
	  System.out.println(beanName);
	}

	if (bean instanceof LoggerService) {
	  ProxyFactory factory = new ProxyFactory(bean);
	  factory.addInterface(LoggerService.class);
	  factory.addAdvice((MethodBeforeAdvice) (method, methodArgs, target) ->
		  log.debug("Method invoked: {}, with args: {}", method.getName(), methodArgs));
	  factory.setExposeProxy(true);
	  return factory.getProxy();
	}
	return bean;
  }
}
