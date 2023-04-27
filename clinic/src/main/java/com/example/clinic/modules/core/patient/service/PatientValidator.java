package com.example.clinic.modules.core.patient.service;

import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.commons.commons.AbstractValidator;
import java.util.HashMap;

public class PatientValidator extends AbstractValidator {

  public void validate(PatientDTO patientDTO) {
	invalidFields = new HashMap<>();

	throwException("Patient");
  }

  private void validateFirstName(String firstName) {

  }
}
