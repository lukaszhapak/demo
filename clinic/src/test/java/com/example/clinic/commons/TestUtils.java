package com.example.clinic.commons;

import com.example.clinic.modules.core.dto.PatientDTO;
import com.example.clinic.modules.core.dto.PatientGender;
import java.time.LocalDate;

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
}
