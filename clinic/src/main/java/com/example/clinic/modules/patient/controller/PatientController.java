package com.example.clinic.modules.patient.controller;

import com.example.clinic.modules.patient.core.PatientFacade;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientController {

  private final PatientFacade patientFacade;

  PatientDTO findById(Long id) {
	return PatientDTO.of(patientFacade.findById(id));
  }

  PatientDTO save(PatientDTO patientDTO) {
	return PatientDTO.of(patientFacade.save(patientDTO.toDomain()));
  }

  PatientDTO update(Long id, PatientDTO patientDTO) {
	return PatientDTO.of(patientFacade.update(id, patientDTO.toDomain()));
  }

  void delete(Long id) {
	patientFacade.deleteById(id);
  }
}
