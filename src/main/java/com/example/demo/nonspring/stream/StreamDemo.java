package com.example.demo.nonspring.stream;

import java.util.List;
import java.util.stream.Collectors;

class StreamDemo {

  public static void main(String[] args) {
	List<Student> students = List.of(new Student("John", List.of(2, 3, 4)), new Student("Jim", List.of(2, 3, 4, 4, 5)));

	List<Student> jo = students.stream().filter(student -> student.getName().startsWith("Jo")).collect(Collectors.toList());

	List<String> collect1 = students.stream().map(Student::getName).collect(Collectors.toList());

	List<Integer> collect2 = students.stream().flatMap(x -> x.getGrades().stream()).collect(Collectors.toList());
  }
}
