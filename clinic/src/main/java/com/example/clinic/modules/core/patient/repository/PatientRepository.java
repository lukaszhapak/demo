package com.example.clinic.modules.core.patient.repository;

import com.example.clinic.modules.core.patient.model.Patient;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

public interface PatientRepository extends Repository<Patient, Long> {
  List<Patient> findAll(Specification<Patient> specification);

  Optional<Patient> findById(Long id);

  Patient save(Patient patient);

  Long deleteAllById(Long id);

  boolean existsByPesel(String pesel);
}
