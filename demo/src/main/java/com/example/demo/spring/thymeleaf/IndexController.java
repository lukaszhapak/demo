package com.example.demo.spring.thymeleaf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
class IndexController {

  @GetMapping("/")
  String index(Model model) {
	String title = "Thymeleaf demo page";
	String text = "Sample text in div";
	model.addAttribute("title", title);
	model.addAttribute("text", text);
	return "index";
  }
}
