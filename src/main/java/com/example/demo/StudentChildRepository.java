package com.example.demo;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface StudentChildRepository extends JpaRepository<StudentChild, Long> {
}
