package com.example.demo.test.unit.archunit.layered.controller;

import com.example.demo.test.unit.archunit.layered.service.PatientService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PatientController {

  private final PatientService patientService;
}
