package com.example.clinic.modules.core.patient.controller;

import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.clinic.modules.core.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;

  @GetMapping("/api/patient/{id}")
  PatientDTO findById(@PathVariable Long id) {
	return patientService.findById(id);
  }
  @GetMapping("/api/patient")
  public PageImpl<PatientDTO> findByQuery(@RequestParam(defaultValue = "") String query, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
	return patientService.findByQuery(query, page, size);
  }

  @PostMapping("/api/patient")
  PatientDTO save(@RequestBody PatientDTO patientDTO) {
	return patientService.save(patientDTO);
  }

  @PutMapping("/api/patient/{id}")
  PatientDTO update(@PathVariable Long id, @RequestBody PatientDTO patientDTO) {
	return patientService.update(id, patientDTO);
  }

  @DeleteMapping("/api/patient/{id}")
  void delete(@PathVariable Long id) {
	patientService.deleteById(id);
  }
}
