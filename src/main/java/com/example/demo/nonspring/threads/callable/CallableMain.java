package com.example.demo.nonspring.threads.callable;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class CallableMain {

  public static void main(String[] args) throws ExecutionException, InterruptedException {

//	System.out.println(getResult());
//	System.out.println(getMultipleResults());
	System.out.println(invokeAll());

  }

  private static ExecutorService getExecutorService() {
	return Executors.newFixedThreadPool(4);
  }

  private static Callable<String> getCallable() {
	return () -> {
	  log.debug("starting");
	  Thread.sleep(2000);
	  log.debug("after sleep");
	  return "test-" + UUID.randomUUID();
	};
  }

  private static String getResult() throws InterruptedException, ExecutionException {
	ExecutorService executorService = getExecutorService();

	log.debug("submit");
	Future<String> submit = executorService.submit(getCallable());

	log.debug("is done");
	boolean done = submit.isDone();

	log.debug("getting result");
	String result = submit.get();

	// it can take timeout as parameter
//	String result = submit.get(500, TimeUnit.MILLISECONDS);

	log.debug("got result");
	executorService.shutdown();

	return result;
  }

  private static List<String> getMultipleResults() throws InterruptedException, ExecutionException {
	ExecutorService executorService = getExecutorService();
	Callable<String> callable = getCallable();

	Future<String> submit1 = executorService.submit(callable);
	Future<String> submit2 = executorService.submit(callable);
	Future<String> submit3 = executorService.submit(callable);
	Future<String> submit4 = executorService.submit(callable);

	String result1 = submit1.get();
	String result2 = submit2.get();
	String result3 = submit3.get();
	String result4 = submit4.get();

	executorService.shutdown();

	return List.of(result1, result2, result3, result4);
  }

  private static List<String> invokeAll() throws InterruptedException, ExecutionException {
	ExecutorService executorService = getExecutorService();
	Callable<String> callable = getCallable();

	List<Callable<String>> callables = List.of(callable, callable, callable, callable);

	List<Future<String>> futures = executorService.invokeAll(callables);

	List<String> collect = futures.stream().map(x -> {
	  try {
		return x.get();
	  } catch (InterruptedException | ExecutionException e) {
		throw new RuntimeException(e);
	  }
	}).collect(Collectors.toList());

	executorService.shutdown();
	return collect;
  }
}
