package com.example.demo.nonspring.threads.executorService;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ExecutorServiceMain {

  public static void main(String[] args) {

//	RestTemplate restTemplate = new RestTemplate();

	threadPoolExecutorService();
	fixedPoolExecutorService();
	singleThreadExecutorService();
  }

  private static void threadPoolExecutorService() {
	LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);
	ExecutorService executorService = new ThreadPoolExecutor(4, 4, 60000L, TimeUnit.MILLISECONDS, workQueue);

	for (int i = 0; i < 4; i++) {
	  executorService.submit(() -> log.debug("executing some operation"));
	}

	executorService.shutdown();
  }

  private static void fixedPoolExecutorService() {
	ExecutorService executorService = Executors.newFixedThreadPool(4);

	for (int i = 0; i < 4; i++) {
	  executorService.submit(() -> log.debug("executing some operation"));
	}

	executorService.shutdown();
  }

  private static void singleThreadExecutorService() {
	ExecutorService executorService = Executors.newSingleThreadExecutor();

	for (int i = 0; i < 4; i++) {
	  executorService.submit(() -> log.debug("executing some operation"));
	}

	executorService.shutdown();
  }
}
