package com.example.clinic.modules.core.patient.config;

import com.example.clinic.modules.core.patient.repository.PatientRepository;
import com.example.clinic.modules.core.patient.service.PatientService;
import com.example.clinic.modules.core.patient.service.PatientValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientConfiguration {

  @Bean
  public PatientService patientService(PatientRepository patientRepository) {
	PatientValidator patientValidator = new PatientValidator(patientRepository);
	return new PatientService(patientRepository, patientValidator);
  }
}
