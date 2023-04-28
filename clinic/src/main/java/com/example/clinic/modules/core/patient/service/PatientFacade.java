package com.example.clinic.modules.core.patient.service;

import com.example.clinic.modules.core.patient.model.Patient;
import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.clinic.modules.core.patient.repository.PatientRepository;
import com.example.commons.exception.NotFoundException;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class PatientFacade {

  private final PatientRepository patientRepository;
  private final PatientValidator patientValidator;

  public PatientDTO findById(Long id) {
	return getPatient(id).toDTO();
  }

  public PatientDTO save(PatientDTO patientDTO) {
	patientValidator.validate(patientDTO);
	Patient patient = Patient.of(patientDTO);
	return patientRepository.save(patient).toDTO();
  }

  public PatientDTO update(Long id, PatientDTO patientDTO) {
	patientValidator.validate(patientDTO);
	Patient toUpdate = getPatient(id);
	toUpdate.update(patientDTO);
	return toUpdate.toDTO();
  }

  public void deleteById(Long id) {
	if (patientRepository.deleteAllById(id) == 0) {
	  throw new NotFoundException(MessageFormat.format("Patient with id={0} not found", id));
	}
  }

  private Patient getPatient(Long id) {
	return patientRepository.findById(id).orElseThrow(() -> new NotFoundException(MessageFormat.format("Patient with id={0} not found", id)));
  }
}
