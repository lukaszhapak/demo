package com.example.demo.spring.modules.data.jpa.search.queryConcat;

import com.example.demo.spring.base.dto.StudentSearchCriteria;
import com.example.demo.spring.base.entity.Student;
import java.util.List;

interface QueryConcatStudentSearchRepository {

  List<Student> findAll(StudentSearchCriteria criteria);
}
