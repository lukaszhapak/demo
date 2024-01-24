package com.example.demo.spring.beanprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

class CustomBeanPostProcessor implements BeanPostProcessor { // todo

  @Override
  public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
	return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
  }

  @Override
  public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
	return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
  }
}
