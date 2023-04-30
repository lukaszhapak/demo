package com.example.clinic.modules.core.patient.service;

import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.commons.commons.AbstractValidator;
import java.util.HashMap;

public class PatientValidator extends AbstractValidator {

  public void validate(PatientDTO patientDTO) {
	invalidFields = new HashMap<>();
	validateString(patientDTO.getFirstName(), 2, 64, "firstName");
	validateString(patientDTO.getLastName(), 2, 64, "lastName");
	validateString(patientDTO.getPesel(), 11, 11, "pesel");
	validateString(patientDTO.getPhoneNumber(), 9, 9, "phoneNumber");
	throwException("Patient");
  }
}
