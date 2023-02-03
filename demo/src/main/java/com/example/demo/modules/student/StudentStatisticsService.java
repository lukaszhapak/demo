package com.example.demo.modules.student;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
class StudentStatisticsService {

  private final StudentRepository studentRepository;
  private final StudentService studentService;
  private final StudentStatisticsReportGenerator studentStatisticsReportGenerator;

  void calculateStatistics() {
	log.debug("Calculating statistics");
	List<StudentStatisticsDTO> studentStatistics = getStudentStatistics();
	List<Integer> allGrades = getAllGrades(studentStatistics);
	if (allGrades.size() > 0) {
	  Double average = calculateAverage(allGrades);
	  Student bestStudent = studentService.getStudentById(getBestStudent(studentStatistics).getId());
	  studentStatisticsReportGenerator.generateReport(bestStudent, studentStatistics.size(), allGrades.size(), average);
	}
  }

  private List<StudentStatisticsDTO> getStudentStatistics() {
	return studentRepository.getStudentStatistics();
  }

  private StudentStatisticsDTO getBestStudent(List<StudentStatisticsDTO> studentStatistics) {
	return studentStatistics.stream()
		.filter(student -> student.getGrades() != null && student.getGrades().size() > 0)
		.max(Comparator.comparing(student -> calculateAverage(student.getGrades())))
		.get();
  }

  private List<Integer> getAllGrades(List<StudentStatisticsDTO> studentStatistics) {
	return studentStatistics.stream()
		.filter(student -> student.getGrades() != null && student.getGrades().size() > 0)
		.map(StudentStatisticsDTO::getGrades)
		.flatMap(List::stream)
		.collect(Collectors.toList());
  }

  private Double calculateAverage(List<Integer> grades) {
	if (grades != null && grades.size() > 0) {
	  double average = (double) grades.stream()
		  .reduce(0, Integer::sum) / grades.size();
	  BigDecimal bigDecimalAverage = new BigDecimal(average).setScale(2, RoundingMode.HALF_UP);
	  return bigDecimalAverage.doubleValue();
	}
	return null;
  }
}
