package com.example.clinic.modules.core.patient.repository;

import com.example.clinic.modules.core.patient.model.Patient;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

public interface PatientRepository extends Repository<Patient, Long> {

  Page<Patient> findAll(Specification<Patient> specification);

  Optional<Patient> findById(Long id);

  Patient save(Patient patient);

  Long deleteAllById(Long id);

  boolean existsByPesel(String pesel);
}
