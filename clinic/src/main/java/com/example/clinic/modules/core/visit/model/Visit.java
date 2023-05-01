package com.example.clinic.modules.core.visit.model;

import com.example.clinic.modules.core.patient.model.Patient;
import com.example.commons.commons.AbstractEntity;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Visit extends AbstractEntity {

  private LocalDateTime visitDate;
  @Enumerated(EnumType.STRING)
  private VisitPaymentStatus paymentStatus;
  @Enumerated(EnumType.STRING)
  private VisitStatus status;
  private boolean reminderSent;
  @ManyToOne
  private Patient patient;

  public VisitDTO toDTO() {
	return VisitDTO.builder()
		.id(getId())
		.visitDate(visitDate)
		.paymentStatus(paymentStatus)
		.status(status)
		.reminderSent(reminderSent)
		.patient(patient.toDTO())
		.build();
  }

  public static Visit of(VisitDTO visitDTO) {
	return Visit.builder()
		.visitDate(visitDTO.getVisitDate())
		.paymentStatus(visitDTO.getPaymentStatus())
		.status(visitDTO.getStatus())
		.reminderSent(visitDTO.isReminderSent())
		.build();
  }
}
