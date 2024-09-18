package com.example.demo.nonspring.threads.restClient;

import java.time.Instant;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.client.RestTemplate;

@Slf4j
class RestClientMain {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
	RestTemplate restTemplate = new RestTemplate();

	long start = Instant.now().toEpochMilli();

	ExecutorService executorService = Executors.newFixedThreadPool(1000);

	for (int i = 0; i < 1000; i++) {
	  executorService.submit(() -> restTemplate.getForEntity("http://localhost:8080/api/student", Student[].class));
	}

	long end = Instant.now().toEpochMilli();

	log.debug("finito, it took={}ms", end - start);

	executorService.shutdown();

  }

}
