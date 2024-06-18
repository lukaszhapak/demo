package com.example.demo.spring.core.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
class LoggingAspect {

  @Around("@annotation(com.example.demo.spring.core.aop.Logging)")
  public Object log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
	Logger log = LoggerFactory.getLogger(proceedingJoinPoint.getSignature().getDeclaringType());
	log.debug("Starting {},  args={}", proceedingJoinPoint.toShortString(), proceedingJoinPoint.getArgs());
	StopWatch stopWatch = new StopWatch();
	stopWatch.start();
	Object result = proceedingJoinPoint.proceed();
	stopWatch.stop();
	log.debug("It took {}ms", stopWatch.getTotalTimeMillis());
	return result;
  }
}
