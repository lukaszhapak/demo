package com.example.clinic.modules.patient.domain;

import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.domain.PatientGender;
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
