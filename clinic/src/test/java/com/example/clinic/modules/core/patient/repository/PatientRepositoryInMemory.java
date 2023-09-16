package com.example.clinic.modules.core.patient.repository;

import com.example.clinic.modules.core.patient.model.Patient;
import com.example.commons.test.AbstractRepositoryInMemory;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

public class PatientRepositoryInMemory extends AbstractRepositoryInMemory<Patient> implements PatientRepository {

  @Override
  public Page<Patient> findAll(Specification<Patient> specification) {
	return null;
  }

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
