package com.example.clinic.modules.core.domain;


import com.example.clinic.modules.core.dto.VisitDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class VisitFacade {

  private final VisitRepository visitRepository;
  private final VisitValidator visitValidator;

  VisitDTO save(VisitDTO visitDTO) {
	visitValidator.validate(visitDTO);
	Visit visit = Visit.of(visitDTO);
	return visitRepository.save(visit).toDTO();
  }

}
