package com.example.clinic.commons.helper;

import com.example.clinic.modules.patient.controller.PatientDTO;
import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.domain.Sex;
import java.time.LocalDate;

public interface TestUtils {

  static PatientDTO getPatientDTO() {
	PatientDTO patientDTO = new PatientDTO();
	patientDTO.setFirstName("John");
	patientDTO.setLastname("Doe");
	patientDTO.setPesel("98654273941");
	patientDTO.setSex(Sex.MALE);
	patientDTO.setBirthDate(LocalDate.of(1998, 2, 15));
	patientDTO.setPhoneNumber("987468231");
	return patientDTO;
  }

  static Patient getPatient(Long id) {
	PatientDTO patientDTO = getPatientDTO();
	Patient patient = patientDTO.toDomain();
	patient.setId(id);
	return patient;
  }
}
