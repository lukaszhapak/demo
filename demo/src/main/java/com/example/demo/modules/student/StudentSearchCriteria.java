package com.example.demo.modules.student;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
class StudentSearchCriteria {

  String sortBy;
  Boolean sortAscending;
  Integer page;
  Integer size;

  Integer id;
  Integer minAge;
  Integer maxAge;
  String name;
}
