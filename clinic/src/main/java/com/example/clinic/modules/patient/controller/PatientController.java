package com.example.clinic.modules.patient.controller;

import com.example.clinic.modules.patient.service.PatientService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;

  PatientDTO findById(Long id) {
	return PatientDTO.of(patientService.findById(id));
  }

  PatientDTO save(PatientDTO patientDTO) {
	return PatientDTO.of(patientService.save(patientDTO.toDomain()));
  }

  PatientDTO update(Long id, PatientDTO patientDTO) {
	return PatientDTO.of(patientService.update(id, patientDTO.toDomain()));
  }

  void delete(Long id) {
	patientService.deleteById(id);
  }
}
