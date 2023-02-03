package com.example.demo.modules.student.statistics;

import com.example.demo.modules.student.domain.Student;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class StudentStatisticsReportGenerator {

  public void generateReport(Student bestStudent, Integer studentsCount, Integer gradesCount, Double average) {
	log.debug("There are {} - students in total, {} - grades and the average is equal to {}", studentsCount, gradesCount, average);
	log.debug("Best student is {}", bestStudent);
  }
}
