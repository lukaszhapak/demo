package com.example.clinic.modules.patient.service;

import com.example.clinic.exception.NotFoundException;
import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.repository.PatientRepository;
import com.example.clinic.modules.patient.validator.PatientValidator;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;
  private final PatientValidator patientValidator;

  public Patient findById(Long id) {
	return getPatient(id);
  }

  public Patient save(Patient patient) {
	patientValidator.validate(patient);
	return patientRepository.save(patient);
  }

  public Patient update(Long id, Patient patient) {
	Patient toUpdate = getPatient(id);
	toUpdate.update(patient);
	return toUpdate;
  }

  public void deleteById(Long id) {
	if (patientRepository.deleteAllById(id) == 0) {
	  throw new NotFoundException(MessageFormat.format("Patient with id={} not found", id));
	}
  }


  private Patient getPatient(Long id) {
	return patientRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat.format("Patient with id={} not found", id)));
  }
}
