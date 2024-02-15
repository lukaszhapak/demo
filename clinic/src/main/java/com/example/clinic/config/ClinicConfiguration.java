package com.example.clinic.config;

import com.example.commons.exception.Advice;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ClinicConfiguration {

  @Bean
  Advice advice() {
	return new Advice();
  }
}
