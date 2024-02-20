package com.example.clinic.modules.core.domain;

import static javax.persistence.CascadeType.REMOVE;
import static javax.persistence.EnumType.STRING;

import com.example.clinic.modules.core.dto.PatientDTO;
import com.example.clinic.modules.core.dto.PatientGender;
import com.example.commons.commons.AbstractEntity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Patient extends AbstractEntity {

  private String firstName;
  private String lastName;
  private String pesel;
  @Enumerated(value = STRING)
  private PatientGender gender;
  private LocalDate birthDate;
  private String phoneNumber;
  @OneToMany(mappedBy = "patient", cascade = REMOVE)
  private List<Visit> visits;

  void update(PatientDTO patientDTO) {
	firstName = patientDTO.getFirstName();
	lastName = patientDTO.getLastName();
	phoneNumber = patientDTO.getPhoneNumber();
  }

  PatientDTO toDTO() {
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

  public void addVisit(Visit visit) {
	visits = visits == null ? new ArrayList<>() : visits;
	visits.add(visit);
	visit.setPatient(this);
  }
}
