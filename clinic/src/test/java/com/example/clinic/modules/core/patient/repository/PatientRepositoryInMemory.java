package com.example.clinic.modules.core.patient.repository;

import com.example.clinic.commons.AbstractRepositoryInMemory;
import com.example.clinic.modules.core.patient.model.Patient;
import com.example.clinic.modules.core.patient.repository.PatientRepository;
import java.util.Optional;

public class PatientRepositoryInMemory extends AbstractRepositoryInMemory<Patient> implements PatientRepository {

  @Override
  public Optional<Patient> findById(Long id) {
	return super.findById(id);
  }

  @Override
  public Patient save(Patient patient) {
	return super.save(patient);
  }

  @Override
  public Long deleteAllById(Long id) {
	return super.deleteAllById(id);
  }
}
