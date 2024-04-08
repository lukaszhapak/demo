package com.example.demo.spring.core.beanLifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

class Service implements InitializingBean, DisposableBean {

  private final OrderService orderService;

  public Service(OrderService orderService) {
	this.orderService = orderService;
	System.out.println("constructor");
	orderService.store("constructor");
  }

  @Override
  public void destroy() {
	System.out.println("destroy from disposable bean");
  }

  @Override
  public void afterPropertiesSet() {
	System.out.println("after properties set from initializing bean");
	orderService.store("after properties set from initializing bean");
  }

  @PostConstruct
  public void postConstruct() {
	System.out.println("post construct annotation");
	orderService.store("post construct annotation");
  }

  @PreDestroy
  public void preDestroy() {
	System.out.println("pre destroy annotation");
  }

  public void initMethod() {
	System.out.println("init method");
	orderService.store("init method");
  }

  public void destroyMethod() {
	System.out.println("destroy method");
  }
}
