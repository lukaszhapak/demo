package com.example.clinic.commons;

import com.example.clinic.modules.patient.controller.PatientDTO;
import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.domain.PatientGender;
import java.time.LocalDate;

public interface TestUtils {

  static PatientDTO getPatientDTO() {
	PatientDTO patientDTO = new PatientDTO();
	patientDTO.setFirstName("John");
	patientDTO.setLastName("Doe");
	patientDTO.setPesel("98654273941");
	patientDTO.setPatientGender(PatientGender.MALE);
	patientDTO.setBirthDate(LocalDate.of(1998, 2, 15));
	patientDTO.setPhoneNumber("987468231");
	return patientDTO;
  }

  static Patient getPatient() {
	PatientDTO patientDTO = getPatientDTO();
	Patient patient = patientDTO.toDomain();
	return patient;
  }
}
