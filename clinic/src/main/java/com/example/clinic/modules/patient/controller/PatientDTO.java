package com.example.clinic.modules.patient.controller;

import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.domain.PatientGender;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {

  private Long id;
  private String firstName;
  private String lastname;
  private String pesel;
  private PatientGender patientGender;
  private LocalDate birthDate;
  private String phoneNumber;

  public Patient toDomain() {
	Patient patient = new Patient();
	patient.setFirstName(firstName);
	patient.setLastname(lastname);
	patient.setPesel(pesel);
	patient.setPatientGender(patientGender);
	patient.setBirthDate(birthDate);
	patient.setPhoneNumber(phoneNumber);
	return patient;
  }

  public static PatientDTO of(Patient patient) {
	PatientDTO patientDTO = new PatientDTO();
	patientDTO.setId(patient.getId());
	patientDTO.setFirstName(patient.getFirstName());
	patientDTO.setLastname(patient.getLastname());
	patientDTO.setPesel(patient.getPesel());
	patientDTO.setPatientGender(patient.getPatientGender());
	patientDTO.setBirthDate(patient.getBirthDate());
	patientDTO.setPhoneNumber(patient.getPhoneNumber());
	return patientDTO;
  }
}
