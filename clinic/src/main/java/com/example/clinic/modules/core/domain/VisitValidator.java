package com.example.clinic.modules.core.domain;

import com.example.clinic.modules.core.dto.VisitDTO;
import com.example.commons.commons.AbstractValidator;

class VisitValidator extends AbstractValidator {

  void validate(VisitDTO visitDTO) {
	validateFutureDate(visitDTO.getDate(), "date");
	throwException("Visit");
  }
}
