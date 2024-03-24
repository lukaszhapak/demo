package com.example.demo.spring.web.controller.thymeleaf;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
class StudentController {

  private final StudentService studentService;

  @GetMapping
  String list(Model model) {
	model.addAttribute("list", studentService.findAll());
	return "/student/list";
  }

  @GetMapping("/{id}")
  String student(Model model, @PathVariable int id) {
	model.addAttribute("student", studentService.findById(id));
	return "/student/student";
  }

  @GetMapping("/add")
  String addForm(Model model) {
	model.addAttribute("student", new Student());
	return "/student/add";
  }

  @PostMapping("/add")
  String add(Student student) {
	studentService.save(student);
	return "redirect:/student";
  }

  @GetMapping("/{id}/update")
  String updateForm(Model model, @PathVariable int id) {
	model.addAttribute("student", studentService.findById(id));
	return "/student/update";
  }

  @PostMapping("/{id}/update")
  String update(Student student) {
	studentService.save(student);
	return "redirect:/student/" + student.getId();
  }

  @GetMapping("/{id}/delete")
  String delete(@PathVariable int id) {
	studentService.deleteById(id);
	return "redirect:/student";
  }
}

