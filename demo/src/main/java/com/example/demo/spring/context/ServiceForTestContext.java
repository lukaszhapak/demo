package com.example.demo.spring.context;

import org.springframework.stereotype.Service;

@Service
class ServiceForTestContext {

  void runSomeMethod(){
    System.out.println("hello");
  }
}
