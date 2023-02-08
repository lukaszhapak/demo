package com.example.clinic.config;

import com.example.clinic.modules.core.patient.service.PatientFacade;
import com.example.clinic.modules.core.patient.service.PatientValidator;
import com.example.clinic.modules.core.patient.repository.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClinicConfiguration {

  @Bean
  public PatientFacade patientFacade(PatientRepository patientRepository) {
	PatientValidator patientValidator = new PatientValidator();
	return new PatientFacade(patientRepository, patientValidator);
  }
}
