package com.example.clinic.modules.core.visit.service;

import com.example.clinic.modules.core.visit.model.VisitDTO;
import com.example.commons.commons.AbstractValidator;

public class VisitValidator extends AbstractValidator {

  public void validate(VisitDTO visitDTO) {
	throwException("Visit");
  }

}
