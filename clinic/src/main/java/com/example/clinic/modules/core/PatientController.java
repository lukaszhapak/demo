package com.example.clinic.modules.core;

import com.example.clinic.modules.core.domain.PatientFacade;
import com.example.clinic.modules.core.dto.PatientDTO;
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
class PatientController {

  private final PatientFacade patientFacade;

  @GetMapping("/api/patient/{id}")
  PatientDTO findById(@PathVariable Long id) {
	return patientFacade.findById(id);
  }

  @PostMapping("/api/patient")
  PatientDTO save(@RequestBody PatientDTO patientDTO) {
	return patientFacade.save(patientDTO);
  }

  @PutMapping("/api/patient/{id}")
  PatientDTO update(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
	return patientFacade.update(id, patientDTO);
  }

  @DeleteMapping("/api/patient/{id}")
  void delete(@PathVariable Long id) {
	patientFacade.deleteById(id);
  }
}
