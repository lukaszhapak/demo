package com.example.clinic.modules.patient.core;

import com.example.clinic.commons.AbstractValidator;
import com.example.clinic.modules.patient.domain.Patient;
import java.util.HashMap;

public class PatientValidator extends AbstractValidator {

  public void validate(Patient patient) {
    invalidFields = new HashMap<>();
    throwException("Patient");
  }
}
