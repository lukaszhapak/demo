package com.example.demo.spring.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
class LoggingAspect {

  @Around("@annotation(com.example.demo.spring.core.aop.Logging)")
  public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
	Logger log = LoggerFactory.getLogger(proceedingJoinPoint.getSignature().getDeclaringType());
	log.debug("Starting {},  args={}", proceedingJoinPoint.toShortString(), proceedingJoinPoint.getArgs());
	Object result = proceedingJoinPoint.proceed();
	return result;
  }
}
