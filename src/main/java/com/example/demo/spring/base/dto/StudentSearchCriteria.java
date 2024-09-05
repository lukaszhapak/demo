package com.example.demo.spring.base.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentSearchCriteria {

  private StudentSearchCriteriaSortBy sortBy;
  private Boolean sortAscending;
  private Integer page;
  private Integer size;

  private String search;
  private Integer id;
  private String firstName;
  private List<String> lastNames;
  private String streetName;
  private Integer olderThan;
  private Integer minimalAge;
  private LocalDateTime dateBefore;
  private LocalDateTime dateAfter;

}
