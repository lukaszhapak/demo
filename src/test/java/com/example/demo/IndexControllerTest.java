package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

public class IndexControllerTest {

  private final IndexController indexController = new IndexController();

  @Test
  void test() {
    ModelAndView modelAndView = indexController.index();
  }
}
