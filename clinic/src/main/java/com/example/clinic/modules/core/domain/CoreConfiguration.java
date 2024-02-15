package com.example.clinic.modules.core.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class CoreConfiguration {

  @Bean
  PatientFacade patientFacade(PatientRepository patientRepository) {
	PatientValidator patientValidator = new PatientValidator(patientRepository);
	return new PatientFacade(patientRepository, patientValidator);
  }

  @Bean
  VisitFacade visitFacade(VisitRepository visitRepository) {
	VisitValidator visitValidator = new VisitValidator();
	return new VisitFacade(visitRepository, visitValidator);
  }
}
