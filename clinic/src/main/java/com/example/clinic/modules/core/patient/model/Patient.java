package com.example.clinic.modules.core.patient.model;

import static javax.persistence.EnumType.STRING;

import com.example.clinic.commons.AbstractEntity;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Patient extends AbstractEntity {

  private String firstName;
  private String lastName;
  private String pesel;
  @Enumerated(value = STRING)
  private PatientGender patientGender;
  private LocalDate birthDate;
  private String phoneNumber;

  public void update(PatientDTO patientDTO) {
	firstName = patientDTO.getFirstName();
	lastName = patientDTO.getLastName();
	pesel = patientDTO.getPesel();
	patientGender = patientDTO.getPatientGender();
	birthDate = patientDTO.getBirthDate();
	phoneNumber = patientDTO.getPhoneNumber();
  }

  public PatientDTO toDTO() {
	return PatientDTO.builder()
		.id(getId())
		.firstName(firstName)
		.lastName(lastName)
		.pesel(pesel)
		.patientGender(patientGender)
		.birthDate(birthDate)
		.phoneNumber(phoneNumber)
		.build();
  }

  public static Patient of(PatientDTO patientDTO) {
	return Patient.builder()
		.firstName(patientDTO.getFirstName())
		.lastName(patientDTO.getLastName())
		.pesel(patientDTO.getPesel())
		.patientGender(patientDTO.getPatientGender())
		.birthDate(patientDTO.getBirthDate())
		.phoneNumber(patientDTO.getPhoneNumber())
		.build();
  }
}
