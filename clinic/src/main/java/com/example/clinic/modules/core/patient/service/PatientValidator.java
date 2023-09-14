package com.example.clinic.modules.core.patient.service;

import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.clinic.modules.core.patient.repository.PatientRepository;
import com.example.commons.commons.AbstractValidator;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class PatientValidator extends AbstractValidator {

  private final PatientRepository patientRepository;

  public void validate(PatientDTO patientDTO) {
	log.debug("Validating patient patientDTO={}", patientDTO);
	invalidFields = new HashMap<>();
	validateString(patientDTO.getFirstName(), 2, 64, "firstName");
	validateString(patientDTO.getLastName(), 2, 64, "lastName");
	validateString(patientDTO.getPesel(), 11, 11, "pesel");
	validateString(patientDTO.getPhoneNumber(), 9, 9, "phoneNumber");
	throwException("Patient");
  }

  public void validatePeselUniqueness(String pesel) {
	if (patientRepository.existsByPesel(pesel)){
	  invalidFields.put("pesel", "pesel must be unique");
	  throwException("Patient");
	}
  }
}
