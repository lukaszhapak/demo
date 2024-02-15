package com.example.demo.nonspring.threads;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class MyThread extends Thread {

  private final Service service;
  private int numberOfExecutions;

  @Override
  public void run() {
	while (numberOfExecutions > 0) {
	  service.execute();
	  numberOfExecutions--;
	}
  }
}
