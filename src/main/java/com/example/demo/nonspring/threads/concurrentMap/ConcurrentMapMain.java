package com.example.demo.nonspring.threads.concurrentMap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

@Slf4j
class ConcurrentMapMain {

  static ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();
  static HashMap<String, String> hashMap = new HashMap<>();

  public static void main(String[] args) {

	concurrentHashMap.put("key", "value");
	hashMap.put("key", "value");

  }
}
