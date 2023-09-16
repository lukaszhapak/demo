package com.example.clinic.modules.core.patient.service;

import com.example.clinic.modules.core.patient.model.Patient;
import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.clinic.modules.core.patient.repository.PatientRepository;
import com.example.commons.exception.NotFoundException;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;
  private final PatientValidator patientValidator;

  public PatientDTO findById(Long id) {
	log.debug("Getting patient id={}", id);
	return getPatient(id).toDTO();
  }

  public Page<PatientDTO> findByQuery(String query, int page, int size) {
	Pageable pageRequest = PageRequest.of(page, size, Sort.by("id"));
	return null;
  }

  public PatientDTO save(PatientDTO patientDTO) {
	log.debug("Saving patient patientDTO={}", patientDTO);
	patientValidator.validate(patientDTO);
	patientValidator.validatePeselUniqueness(patientDTO.getPesel());
	Patient patient = Patient.of(patientDTO);
	return patientRepository.save(patient).toDTO();
  }

  public PatientDTO update(Long id, PatientDTO patientDTO) {
	log.debug("Updating patient id={} patientDTO={}", id, patientDTO);
	patientValidator.validate(patientDTO);
	Patient toUpdate = getPatient(id);
	toUpdate.update(patientDTO);
	return toUpdate.toDTO();
  }

  public void deleteById(Long id) {
	log.debug("Deleting patient id={}", id);
	if (patientRepository.deleteAllById(id) == 0) {
	  throw new NotFoundException(MessageFormat.format("Patient with id={0} not found", id));
	}
  }

  private Patient getPatient(Long id) {
	return patientRepository.findById(id)
		.orElseThrow(() -> new NotFoundException(MessageFormat.format("Patient with id={0} not found", id)));
  }
}
