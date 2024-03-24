package com.example.demo.spring.web.controller.thymeleaf;

import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<Student, Long> {

}

