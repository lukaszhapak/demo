package com.example.clinic.modules.patient.core;

import com.example.clinic.commons.exception.NotFoundException;
import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.repository.PatientRepository;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class PatientFacadeImpl implements PatientFacade {

  private final PatientRepository patientRepository;
  private final PatientValidator patientValidator;

  @Override
  public Patient findById(Long id) {
	return getPatient(id);
  }

  @Override
  public Patient save(Patient patient) {
	patientValidator.validate(patient);
	return patientRepository.save(patient);
  }

  @Override
  public Patient update(Long id, Patient patient) {
	Patient toUpdate = getPatient(id);
	toUpdate.update(patient);
	return toUpdate;
  }

  @Override
  public void deleteById(Long id) {
	if (patientRepository.deleteAllById(id) == 0) {
	  throw new NotFoundException(MessageFormat.format("Patient with id={} not found", id));
	}
  }

  private Patient getPatient(Long id) {
	return patientRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat.format("Patient with id={0} not found", id)));
  }
}
