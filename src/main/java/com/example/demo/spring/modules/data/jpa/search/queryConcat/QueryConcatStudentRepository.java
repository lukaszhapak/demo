package com.example.demo.spring.modules.data.jpa.search.queryConcat;

import com.example.demo.spring.base.entity.Student;
import org.springframework.data.repository.Repository;

interface QueryConcatStudentRepository extends Repository<Student, Long>, QueryConcatStudentSearchRepository {

  Student save(Student student);

}
