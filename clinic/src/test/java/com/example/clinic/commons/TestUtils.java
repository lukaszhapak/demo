package com.example.clinic.commons;

import com.example.clinic.modules.core.dto.PatientDTO;
import com.example.clinic.modules.core.dto.PatientGender;
import com.example.clinic.modules.core.dto.VisitDTO;
import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TestUtils {

  static PatientDTO getPatientDTO() {
	return PatientDTO.builder()
		.firstName("John")
		.lastName("Doe")
		.pesel("98654273941")
		.gender(PatientGender.MALE)
		.birthDate(LocalDate.of(1998, 2, 15))
		.phoneNumber("987468231")
		.build();
  }

  static VisitDTO getVisitDTO() {
	return VisitDTO.builder()
		.date(LocalDateTime.now().plusDays(1))
		.build();
  }
}
