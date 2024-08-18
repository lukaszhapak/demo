package com.example.demo.spring.http.server.thymeleaf;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/student")
@RequiredArgsConstructor
class StudentController {

  private final StudentService studentService;

  // i can return model and view
  @GetMapping
  ModelAndView list() {
	ModelAndView modelAndView = new ModelAndView("/student/list");
	modelAndView.addObject("list", studentService.findAll());
	return modelAndView;
  }

  // or just a string as a template and receive model as a parameter
  @GetMapping("/{id}")
  String student(Model model, @PathVariable int id) {
	model.addAttribute("student", studentService.findById(id));
	model.addAttribute("title", "details of student " + id);
	return "/student/details";
  }

  @GetMapping("/add")
  String addForm(Model model) {
	model.addAttribute("student", new Student());
	model.addAttribute("title", "add new student");
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

