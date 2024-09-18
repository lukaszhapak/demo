package com.example.demo.nonspring.memoryLeaks;

import java.util.ArrayList;
import java.util.List;

class MemoryLeaksMain {

  static List<String> list = new ArrayList<>();

  public static void main(String[] args) {
	Runtime runtime = Runtime.getRuntime();

	long max = runtime.maxMemory();
	long total = runtime.totalMemory();
	long freeAtStart = runtime.freeMemory();

	for (int i = 0; i < 10_000_000; i++) {
	  list.add("test" + i);
	}

	long freeAfterPopulatingList = runtime.freeMemory();

	System.gc();



	long freeAfterGC = runtime.freeMemory();
  }

}
