package com.example.clinic.modules.patient.repository;

import static com.example.clinic.commons.helper.TestUtils.getPatient;

import com.example.clinic.commons.helper.AbstractRepositoryInMemory;
import com.example.clinic.modules.patient.domain.Patient;
import java.util.List;
import java.util.Optional;

public class PatientRepositoryInMemory extends AbstractRepositoryInMemory<Patient> implements PatientRepository {

  public void insertData() {
	super.insertData(List.of(getPatient(1000001L)));
  }

  public void cleanData(){
	super.cleanData();
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
}
