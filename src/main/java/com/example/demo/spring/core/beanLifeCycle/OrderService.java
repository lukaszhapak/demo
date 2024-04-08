package com.example.demo.spring.core.beanLifeCycle;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
class OrderService {

  private final List<String> orderList = new ArrayList<>();

  void store(String operation) {
	orderList.add(operation);
  }

  List<String> getList() {
	return orderList;
  }

}
