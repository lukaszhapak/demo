package com.example.demo;

import lombok.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;

    @GetMapping("/student/{id}")
    public Student findById(@PathVariable Long id) {
        return studentService.findById(id);
    }
}
