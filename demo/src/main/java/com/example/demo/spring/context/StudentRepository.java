package com.example.demo.spring.context;

import org.springframework.data.jpa.repository.JpaRepository;

interface StudentRepository extends JpaRepository<Stuudent, Long> {

}
