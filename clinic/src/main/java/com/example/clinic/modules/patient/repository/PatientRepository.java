package com.example.clinic.modules.patient.repository;

import com.example.clinic.modules.patient.domain.Patient;
import java.util.Optional;
import org.springframework.data.repository.Repository;

public interface PatientRepository extends Repository<Patient, Long> {

  Optional<Patient> findById(Long id);

  Patient save(Patient patient);

  Long deleteAllById(Long id);
}
