package com.example.demo.test.unit.inMemoryImplementation.mock;


class StudentMapper {

  Student toDomain(StudentDTO studentDTO) {
	return new Student()
		.setId(studentDTO.getId())
		.setName(studentDTO.getName())
		.setAge(studentDTO.getAge());
  }

  StudentDTO toDTO(Student student) {
	return new StudentDTO()
		.setId(student.getId())
		.setName(student.getName())
		.setAge(student.getAge());
  }
}
