package com.example.demo.spring.data.mongo.mongoSearch;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class StudentSearchCriteria {

  String sortBy;
  Boolean sortAscending;
  Integer page;
  Integer size;

  Integer id;
  String firstName;
  List<String> lastNames;
  String streetName;
  Integer olderThan;
  Integer minimalAge;
  LocalDateTime dateBefore;
  LocalDateTime dateAfter;

}
