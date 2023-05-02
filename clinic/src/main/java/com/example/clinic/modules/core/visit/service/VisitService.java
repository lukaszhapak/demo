package com.example.clinic.modules.core.visit.service;


import com.example.clinic.modules.core.visit.model.Visit;
import com.example.clinic.modules.core.visit.model.VisitDTO;
import com.example.clinic.modules.core.visit.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class VisitService {

  private final VisitRepository visitRepository;
  private final VisitValidator visitValidator;

  public VisitDTO save(VisitDTO visitDTO) {
	visitValidator.validate(visitDTO);
	Visit visit = Visit.of(visitDTO);
	return visitRepository.save(visit).toDTO();
  }

}
