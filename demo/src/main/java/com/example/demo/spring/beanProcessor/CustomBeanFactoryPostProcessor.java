package com.example.demo.spring.beanProcessor;

import static java.util.Arrays.stream;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

  @Override
  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	List<String> stringStream = stream(beanFactory.getBeanDefinitionNames())
		.map(beanFactory::getBeanDefinition)
		.filter(beanDefinition -> beanClassNameContains(beanDefinition, "spring.beanprocessor"))
		.map(BeanDefinition::getBeanClassName).collect(Collectors.toList());

	stringStream.forEach(System.out::println);
  }

  private boolean beanClassNameContains(BeanDefinition beanDefinition, String subString) {
	return beanDefinition.getBeanClassName() != null && beanDefinition.getBeanClassName().contains(subString);
  }
}