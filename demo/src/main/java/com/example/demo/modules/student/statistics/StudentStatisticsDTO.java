package com.example.demo.modules.student.statistics;


import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentStatisticsDTO {

  private final Long id;
  private final List<Integer> grades;
}
