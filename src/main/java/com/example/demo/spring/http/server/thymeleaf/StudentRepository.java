package com.example.demo.spring.http.server.thymeleaf;

import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<Student, Long> {

}

