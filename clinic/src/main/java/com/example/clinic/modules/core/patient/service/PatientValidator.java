package com.example.clinic.modules.core.patient.service;

import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.commons.commons.AbstractValidator;
import java.util.HashMap;

public class PatientValidator extends AbstractValidator {

  public void validate(PatientDTO patientDTO) {
	invalidFields = new HashMap<>();
	validateFirstName(patientDTO.getFirstName());
	validateLastName(patientDTO.getLastName());
	validatePesel(patientDTO.getPesel());
	validatePhoneNumber(patientDTO.getPhoneNumber());
	throwException("Patient");
  }

  private void validateFirstName(String firstName) {
	validateString(firstName, 2, 64, "firstName");
  }

  private void validateLastName(String lastName) {
	validateString(lastName, 2, 64, "lastName");
  }

  private void validatePesel(String pesel) {
	validateString(pesel, 11, 11, "pesel");
  }

  private void validatePhoneNumber(String phoneNumber) {
	validateString(phoneNumber, 9, 9, "phoneNumber");
  }
}
