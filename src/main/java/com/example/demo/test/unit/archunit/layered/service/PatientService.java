package com.example.demo.test.unit.archunit.layered.service;

import com.example.demo.test.unit.archunit.layered.repository.PatientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientService {

  private final PatientRepository patientRepository;

}
