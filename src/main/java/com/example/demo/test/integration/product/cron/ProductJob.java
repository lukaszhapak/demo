package com.example.demo.test.integration.product.cron;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProductJob {

  @Scheduled(cron = "${product.cron}")
  public void testJob() {
	log.debug("test product job");
  }
}
