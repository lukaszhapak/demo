package com.example.demo.nonspring.threads;

import java.time.Instant;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.springframework.web.client.RestTemplate;

class Main {

  public static final int numberOfThreads = 4;
  public static final int numberOfExecutions = 2;

  public static void main(String[] args) {

	// deadlock
	// race condition
	// starvation
	// synchronized
	// volatile
	Service service = new Service();

	RestTemplate restTemplate = new RestTemplate();

	System.out.println("---------->>>> executor service");

	LinkedBlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(100);
	ExecutorService executorService = new ThreadPoolExecutor(100, 100, 60000L, TimeUnit.MILLISECONDS, workQueue);

	LinkedBlockingQueue<Runnable> workQueue2 = new LinkedBlockingQueue<>(100);
	ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 4, 60, TimeUnit.SECONDS, workQueue2);

	long start = Instant.now().toEpochMilli();

	for (int i = 0; i < 100; i++) {
//	  try {
//		Thread.sleep(3L);
//	  } catch (InterruptedException e) {
//		throw new RuntimeException(e);
//	  }
	  executorService.execute(() -> service.execute());

//	  executorService.execute(new Thread(() -> service.execute()));

//	  executorService.execute(() -> restTemplate.getForEntity("http://localhost:8080/api/student", Student[].class));

	}

	executorService.shutdown();

	long end = Instant.now().toEpochMilli();

	System.out.println("operation took: " + (end - start) + "ms");

//	System.out.println("---------->>>> runnable");
//
//	Runnable runnable = () -> service.execute();
//	runnable.run();
//
//	System.out.println("---------->>>> my runnable");
//
//	for (int i = 0; i < numberOfThreads; i++) {
//	  Runnable myRunnable = new MyRunnable(service, numberOfExecutions);
//	  myRunnable.run();
//	}

//	System.out.println("---------->>>> my thread");
//
//	for (int i = 0; i < numberOfThreads; i++) {
//	  Thread myThread = new MyThread(service, numberOfExecutions);
//	  myThread.start();
//	}
  }
}
