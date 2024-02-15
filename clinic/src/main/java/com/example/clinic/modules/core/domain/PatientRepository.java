package com.example.clinic.modules.core.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

interface PatientRepository extends Repository<Patient, Long> {

  Optional<Patient> findById(Long id);

  Patient save(Patient patient);

  Long deleteAllById(Long id);

  boolean existsByPesel(String pesel);
}
