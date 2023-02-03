package com.example.demo.modules.student;


import com.example.demo.commons.helper.MappingHelper;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class StudentStatisticsDTO {

  public StudentStatisticsDTO(Long id, String grades) {
	this.id = id;
	this.grades = MappingHelper.stringAsCollection(grades);
  }

  private Long id;
  private List<Integer> grades;
}
