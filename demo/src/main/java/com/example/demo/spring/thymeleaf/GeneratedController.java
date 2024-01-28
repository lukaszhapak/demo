package com.example.demo.spring.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/generated")
@RequiredArgsConstructor
class GeneratedController {

  private final GeneratedService generatedService;

  @GetMapping
  String list(Model model) {
	model.addAttribute("list", generatedService.findAll());
	return "/generated/list";
  }

  @GetMapping("/{id}")
  String generated(Model model, @PathVariable int id) {
	model.addAttribute("generated", generatedService.findById(id));
	return "/generated/generated";
  }

  @GetMapping("/add")
  String addForm(Model model) {
	model.addAttribute("generated", new Generated());
	return "/generated/add";
  }

  @PostMapping("/add")
  String add(Generated generated) {
	generatedService.save(generated);
	return "redirect:/generated";
  }

  @GetMapping("/{id}/update")
  String updateForm(Model model, @PathVariable int id) {
	model.addAttribute("generated", generatedService.findById(id));
	return "/generated/update";
  }

  @PostMapping("/{id}/update")
  String update(Generated generated) {
	generatedService.save(generated);
	return "redirect:/generated/" + generated.getId();
  }

  @GetMapping("/{id}/delete")
  String delete(@PathVariable int id) {
	generatedService.deleteById(id);
	return "redirect:/generated";
  }
}

