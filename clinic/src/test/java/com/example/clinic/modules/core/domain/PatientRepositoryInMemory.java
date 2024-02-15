package com.example.clinic.modules.core.domain;

import com.example.commons.test.AbstractRepositoryInMemory;
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

  @Override
  public boolean existsByPesel(String pesel) {
	return map.values().stream()
		.anyMatch(x -> x.getPesel().equals(pesel));
  }
}
