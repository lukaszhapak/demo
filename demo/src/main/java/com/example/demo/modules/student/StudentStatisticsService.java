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
  private final StudentStatisticsReportGenerator studentStatisticsReportGenerator;

  void calculateStatistics() {
	log.debug("Calculating statistics");
	List<StudentStatisticsDTO> studentStatistics = getStudentStatistics();
	StudentStatisticsDTO bestStudent = getBestStudent(studentStatistics);
	List<Integer> allGrades = getAllGrades(studentStatistics);
	Double average = calculateAverage(allGrades);
	studentStatisticsReportGenerator.generateReport(bestStudent.getId(), studentStatistics.size(), allGrades.size(), average);
  }

  private StudentStatisticsDTO getBestStudent(List<StudentStatisticsDTO> studentStatistics) {
	return studentStatistics.stream()
		.max(Comparator.comparing(student -> calculateAverage(student.getGrades())))
		.orElse(null);
  }

  private List<Integer> getAllGrades(List<StudentStatisticsDTO> studentStatistics) {
	return studentStatistics.stream()
		.map(StudentStatisticsDTO::getGrades)
		.flatMap(List::stream)
		.collect(Collectors.toList());
  }

  private Double calculateAverage(List<Integer> grades) {
	double average = (double) grades.stream()
		.reduce(0, Integer::sum) / grades.size();
	BigDecimal bigDecimalAverage = new BigDecimal(average).setScale(2, RoundingMode.HALF_UP);
	return bigDecimalAverage.doubleValue();
  }

  private List<StudentStatisticsDTO> getStudentStatistics() {
	return studentRepository.getStudentStatistics();
  }
}
