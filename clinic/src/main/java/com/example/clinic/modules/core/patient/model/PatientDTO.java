package com.example.clinic.modules.core.patient.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String pesel;
  private PatientGender patientGender;
  private LocalDate birthDate;
  private String phoneNumber;
}
