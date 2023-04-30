package com.example.clinic.modules.core.patient.model;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.EnumType.STRING;

import com.example.clinic.modules.core.visit.model.Visit;
import com.example.commons.commons.AbstractEntity;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
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
  private PatientGender gender;
  private LocalDate birthDate;
  private String phoneNumber;
  @OneToMany(mappedBy = "patient", cascade = REMOVE)
  private List<Visit> visits;

  public void update(PatientDTO patientDTO) {
	firstName = patientDTO.getFirstName();
	lastName = patientDTO.getLastName();
	pesel = patientDTO.getPesel();
	gender = patientDTO.getGender();
	birthDate = patientDTO.getBirthDate();
	phoneNumber = patientDTO.getPhoneNumber();
  }

  public PatientDTO toDTO() {
	return PatientDTO.builder()
		.id(getId())
		.firstName(firstName)
		.lastName(lastName)
		.pesel(pesel)
		.gender(gender)
		.birthDate(birthDate)
		.phoneNumber(phoneNumber)
		.build();
  }

  public static Patient of(PatientDTO patientDTO) {
	return Patient.builder()
		.firstName(patientDTO.getFirstName())
		.lastName(patientDTO.getLastName())
		.pesel(patientDTO.getPesel())
		.gender(patientDTO.getGender())
		.birthDate(patientDTO.getBirthDate())
		.phoneNumber(patientDTO.getPhoneNumber())
		.build();
  }
}
