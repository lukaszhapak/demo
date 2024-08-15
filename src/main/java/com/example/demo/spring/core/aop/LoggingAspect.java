package com.example.demo.spring.core.aop;

import static java.lang.String.format;

import java.util.ArrayList;
import java.util.List;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
class LoggingAspect {

  @Pointcut("target(org.springframework.data.repository.Repository)")
  void allRepositories() {
  }

  @Pointcut("@within(org.springframework.stereotype.Service)")
  void allServices() {
  }

  @Pointcut("@within(com.example.demo.spring.core.aop.LoggingBeforeExecution)")
  void logBeforeExecutionAnnotation() {
  }

  @Pointcut("@within(com.example.demo.spring.core.aop.LoggingAfterExecution)")
  void logAfterExecutionAnnotation() {
  }

  @Around("logBeforeExecutionAnnotation() || allServices()")
  Object logBeforeExecution(ProceedingJoinPoint joinPoint) throws Throwable {
	log(joinPoint);
	return joinPoint.proceed();
  }

  @Around("logAfterExecutionAnnotation() || allRepositories() ")
  Object logAfterExecution(ProceedingJoinPoint joinPoint) throws Throwable {
	StopWatch stopWatch = new StopWatch();
	stopWatch.start();
	Object result = joinPoint.proceed();
	stopWatch.stop();
	log(joinPoint, stopWatch, result);
	return result;
  }

  private void log(ProceedingJoinPoint joinPoint, StopWatch stopWatch, Object result) {
	String timerString = createTimerString(stopWatch);
	String resultString = getReturnValue(result);
	if (!timerString.isEmpty() || !resultString.isEmpty()) {
	  Logger logger = getLogger(joinPoint);
	  logger.trace("{} {} {}", getOperationName(joinPoint), timerString, resultString);
	}
  }

  private void log(ProceedingJoinPoint joinPoint) {
	Logger logger = getLogger(joinPoint);
	logger.debug("{}", getOperationName(joinPoint));
  }

  private Logger getLogger(ProceedingJoinPoint joinPoint) {
	return LoggerFactory.getLogger(joinPoint.getSignature().getDeclaringType());
  }

  private String getReturnValue(Object result) {
	return format("returned: %s", result);
  }

  private String createTimerString(StopWatch stopWatch) {
	long millis = stopWatch.getTotalTimeMillis();
	return format("[%d ms]", millis);
  }

  private String getOperationName(ProceedingJoinPoint joinPoint) {
	return joinPoint.getSignature().getName() + " " + getParametersWithNames(joinPoint);
  }

  private List<String> getParametersWithNames(ProceedingJoinPoint proceedingJoinPoint) {
	MethodSignature methodSig = (MethodSignature) proceedingJoinPoint.getSignature();
	Object[] args = proceedingJoinPoint.getArgs();
	String[] parametersName = methodSig.getParameterNames();

	List<String> result = new ArrayList<>();

	for (int i = 0; i < args.length; i++) {
	  result.add(parametersName[i] + "=" + args[i]);
	}
	return result;
  }
}
