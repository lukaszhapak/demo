package com.example.clinic.modules.patient.domain;

import static javax.persistence.EnumType.STRING;

import com.example.clinic.commons.AbstractEntity;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Patient extends AbstractEntity {

  private String firstName;
  private String lastName;
  private String pesel;
  @Enumerated(value = STRING)
  private PatientGender patientGender;
  private LocalDate birthDate;
  private String phoneNumber;

  public void update(Patient patient) {
	firstName = patient.getFirstName();
	lastName = patient.getLastName();
	pesel = patient.getPesel();
	patientGender = patient.getPatientGender();
	birthDate = patient.getBirthDate();
	phoneNumber = patient.getPhoneNumber();
  }
}
