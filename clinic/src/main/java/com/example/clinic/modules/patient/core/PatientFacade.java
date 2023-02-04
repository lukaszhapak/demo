package com.example.clinic.modules.patient.core;

import com.example.clinic.modules.patient.domain.Patient;

public interface PatientFacade {

  Patient findById(Long id);

  Patient save(Patient patient);

  Patient update(Long id, Patient patient);

  void deleteById(Long id);
}
