package com.example.demo.spring.data.jpa.search.queryConcat;

import org.springframework.data.repository.Repository;

interface StudentRepository extends Repository<Student, Long>, StudentSearchRepository {

  Student save(Student student);

}
