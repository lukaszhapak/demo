package com.example.clinic.modules.core.domain;

import org.springframework.data.repository.Repository;

interface VisitRepository extends Repository<Visit, Long> {

  Visit save(Visit visit);

}
