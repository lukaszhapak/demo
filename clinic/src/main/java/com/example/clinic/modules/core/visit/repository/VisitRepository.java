package com.example.clinic.modules.core.visit.repository;

import com.example.clinic.modules.core.visit.model.Visit;
import org.springframework.data.repository.Repository;

public interface VisitRepository extends Repository<Visit, Long> {

  Visit save(Visit visit);

}
