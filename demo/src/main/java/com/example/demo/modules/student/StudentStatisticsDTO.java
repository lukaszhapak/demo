package com.example.demo.modules.student;


import com.example.demo.commons.helper.MappingHelper;
import java.util.List;
import lombok.Getter;

@Getter
class StudentStatisticsDTO {

  public StudentStatisticsDTO(Long id, String grades) {
	this.id = id;
	this.grades = MappingHelper.stringAsCollection(grades);
  }

  private final Long id;
  private final List<Integer> grades;
}
