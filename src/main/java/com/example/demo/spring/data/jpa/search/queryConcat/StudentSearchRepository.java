package com.example.demo.spring.data.jpa.search.queryConcat;

import java.util.List;

interface StudentSearchRepository {

  List<Student> findAll(StudentSearchCriteria criteria);
}
