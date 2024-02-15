package com.example.clinic.modules.core.domain;

import com.example.clinic.modules.core.dto.PatientDTO;
import com.example.commons.exception.NotFoundException;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@RequiredArgsConstructor
public class PatientFacade {

  private final PatientRepository patientRepository;
  private final PatientValidator patientValidator;

  public PatientDTO findById(Long id) {
	log.debug("Getting patient id={}", id);
	return getPatient(id).toDTO();
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
