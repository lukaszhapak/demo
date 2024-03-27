package com.example.demo.spring.data.specification;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
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
