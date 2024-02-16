package com.example.clinic.modules.core.domain;

import com.example.commons.test.AbstractRepositoryInMemory;

public class PatientRepositoryInMemory extends AbstractRepositoryInMemory<Patient> implements PatientRepository {

  @Override
  public boolean existsByPesel(String pesel) {
	return map.values().stream()
		.anyMatch(patient -> patient.getPesel().equals(pesel));
  }
}
