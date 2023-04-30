package com.example.clinic.config;

import com.example.clinic.modules.core.patient.repository.PatientRepository;
import com.example.clinic.modules.core.patient.service.PatientService;
import com.example.clinic.modules.core.patient.service.PatientValidator;
import com.example.commons.exception.Advice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClinicConfiguration {

  @Bean
  public Advice advice() {
	return new Advice();
  }

  @Bean
  public PatientService patientService(PatientRepository patientRepository) {
	PatientValidator patientValidator = new PatientValidator();
	return new PatientService(patientRepository, patientValidator);
  }
}
