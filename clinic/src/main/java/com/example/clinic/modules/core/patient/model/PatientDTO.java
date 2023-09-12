package com.example.clinic.modules.core.patient.model;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {

  private Long id;
  private String firstName;
  private String lastName;
  private String pesel;
  private PatientGender gender;
  private LocalDate birthDate;
  private String phoneNumber;
}
