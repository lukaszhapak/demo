package com.example.clinic.modules.core.domain;


import com.example.clinic.modules.core.dto.VisitDTO;
import com.example.commons.exception.NotFoundException;
import java.text.MessageFormat;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
public class VisitFacade {

  private final VisitRepository visitRepository;
  private final VisitValidator visitValidator;
  private final PatientFacade patientFacade;

  VisitDTO save(Long patientId, VisitDTO visitDTO) {
	visitValidator.validate(visitDTO);
	Visit visit = Visit.of(visitDTO);
	Patient patient = patientFacade.getPatient(patientId);
	patient.addVisit(visit);
	return visitRepository.save(visit).toDTO();
  }

  public VisitDTO findById(Long id) {
	return getVisit(id).toDTO();
  }

  Visit getVisit(Long id) {
	return visitRepository.findById(id)
		.orElseThrow(() -> new NotFoundException(MessageFormat.format("Visit with id={0} not found", id)));
  }
}
