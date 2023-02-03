package com.example.clinic.modules.patient.validator;

import com.example.clinic.commons.AbstractValidator;
import com.example.clinic.modules.patient.domain.Patient;

public class PatientValidator extends AbstractValidator {

  public void validate(Patient patient) {

    throwException("Patient");
  }
}
