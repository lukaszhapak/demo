package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

  @GetMapping("/index")
  public ModelAndView index(){
    ModelAndView mav = new ModelAndView("index");
    mav.addObject("message", "hello world!");
    return mav;
  }
}
