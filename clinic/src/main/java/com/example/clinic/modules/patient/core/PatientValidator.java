package com.example.clinic.modules.patient.core;

import com.example.clinic.commons.AbstractValidator;
import com.example.clinic.modules.patient.domain.PatientDTO;
import java.util.HashMap;

public class PatientValidator extends AbstractValidator {

  public void validate(PatientDTO patientDTO) {
	invalidFields = new HashMap<>();

	throwException("Patient");
  }

  private void validateFirstName(String firstName) {

  }
}
