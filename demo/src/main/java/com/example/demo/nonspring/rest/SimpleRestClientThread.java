package com.example.demo.nonspring.rest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
class SimpleRestClientThread extends Thread {

  private final EntryResourceClient restClient;
  private int numberOfExecutions;

  @Override
  public void run() {
	while (numberOfExecutions > 0) {
	  Entry entry = new Entry();
	  entry.setData("some-data");
	  restClient.processEntry(entry);
	  numberOfExecutions--;
	}
  }
}
