package com.example.clinic.modules.patient.controller;

import com.example.clinic.modules.patient.core.PatientFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PatientController {

  private final PatientFacade patientFacade;

  @GetMapping("/clinic/api/patient/{id}")
  PatientDTO findById(@PathVariable Long id) {
	return PatientDTO.of(patientFacade.findById(id));
  }

  @PostMapping("/clinic/api/patient")
  PatientDTO save(@RequestBody PatientDTO patientDTO) {
	return PatientDTO.of(patientFacade.save(patientDTO.toDomain()));
  }

  @PutMapping("/clinic/api/patient/{id}")
  PatientDTO update(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
	return PatientDTO.of(patientFacade.update(id, patientDTO.toDomain()));
  }

  @DeleteMapping("/clinic/api/patient/{id}")
  void delete(@PathVariable Long id) {
	patientFacade.deleteById(id);
  }
}
