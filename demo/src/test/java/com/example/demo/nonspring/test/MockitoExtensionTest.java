package com.example.demo.nonspring.test;

import static com.example.demo.nonspring.test.TestData.getStudent;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MockitoExtensionTest {

  @Mock
  StudentRepository studentRepository;
  @InjectMocks
  StudentService studentService;

  @Test
  @DisplayName("verify save")
  void verifySave() {
	// given
	Student student = getStudent();

	// when
	studentService.save(student);

	// then
	verify(studentRepository).save(any());
  }
}
