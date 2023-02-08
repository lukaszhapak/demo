package com.example.clinic.commons;

import com.example.clinic.modules.patient.domain.PatientDTO;
import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.domain.PatientGender;
import java.time.LocalDate;

public interface TestUtils {

  static PatientDTO getPatientDTO() {
	return getPatient().toDTO();
  }

  static Patient getPatient() {
	return Patient.builder()
		.firstName("John")
		.lastName("Doe")
		.pesel("98654273941")
		.patientGender(PatientGender.MALE)
		.birthDate(LocalDate.of(1998, 2, 15))
		.phoneNumber("987468231")
		.build();
  }
}
