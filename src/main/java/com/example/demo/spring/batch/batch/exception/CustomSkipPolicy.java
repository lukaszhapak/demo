package com.example.demo.spring.batch.batch.exception;

import org.springframework.batch.core.step.skip.SkipLimitExceededException;
import org.springframework.batch.core.step.skip.SkipPolicy;

public class CustomSkipPolicy implements SkipPolicy {

  @Override
  public boolean shouldSkip(Throwable throwable, int skipCount) throws SkipLimitExceededException {
	return !(throwable instanceof BatchItemReaderException);
  }
}
