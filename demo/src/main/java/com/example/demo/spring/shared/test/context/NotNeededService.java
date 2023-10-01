package com.example.demo.spring.shared.test.context;

import org.springframework.stereotype.Service;

@Service
class NotNeededService {

  public void testMethod() {
	System.out.println("test method");
  }
}
