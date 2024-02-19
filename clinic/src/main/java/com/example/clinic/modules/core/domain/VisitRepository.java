package com.example.clinic.modules.core.domain;

import java.util.Optional;
import org.springframework.data.repository.Repository;

interface VisitRepository extends Repository<Visit, Long> {

  Optional<Visit> findById(Long id);

  Visit save(Visit visit);

}
