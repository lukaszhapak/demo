package com.example.clinic.modules.patient.domain;

import com.example.clinic.commons.AbstractEntity;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Patient extends AbstractEntity {

  private String firstName;
  private String lastname;
  private String pesel;
  private PatientGender patientGender;
  private LocalDate birthDate;
  private String phoneNumber;

  public void update(Patient patient) {
	firstName = patient.getFirstName();
	lastname = patient.getLastname();
	pesel = patient.getPesel();
	patientGender = patient.getPatientGender();
	birthDate = patient.getBirthDate();
	phoneNumber = patient.getPhoneNumber();
  }
}
