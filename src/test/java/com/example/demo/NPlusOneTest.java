package com.example.demo;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;

import java.util.*;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@SpringBootTest(webEnvironment = RANDOM_PORT)
public class NPlusOneTest {
    @Autowired
    StudentChildRepository studentChildRepository;
    @Autowired
    StudentRepository studentRepository;
    Random random = new Random();

    @Test
    void addData() {
        for (int i = 0; i < 500; i++) {
            Student student = new Student("John" + random.nextInt());
            for (int j = 0; j < 50; j++) {
                student.addChild(new StudentChild("child" + random.nextInt()));
            }
            studentRepository.save(student);
        }
    }

    @Test
    void getAllStudent() {
        List<Student> all = studentRepository.findAll();
    }

    @Test
    void getAllChild() {
        List<StudentChild> all = studentChildRepository.findAll();
    }
}
