package com.example.clinic.modules.patient.controller;

import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.domain.Sex;
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
  private Sex sex;
  private LocalDate birthDate;
  private String phoneNumber;

  public Patient toDomain() {
	Patient patient = new Patient();
	patient.setFirstName(firstName);
	patient.setLastname(lastname);
	patient.setPesel(pesel);
	patient.setSex(sex);
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
	patientDTO.setSex(patient.getSex());
	patientDTO.setBirthDate(patient.getBirthDate());
	patientDTO.setPhoneNumber(patient.getPhoneNumber());
	return patientDTO;
  }
}
