package com.example.clinic.modules.core.domain;

import static com.example.clinic.modules.core.dto.VisitPaymentStatus.AWAITING_PAYMENT;
import static com.example.clinic.modules.core.dto.VisitStatus.*;

import com.example.clinic.modules.core.dto.VisitDTO;
import com.example.clinic.modules.core.dto.VisitPaymentStatus;
import com.example.clinic.modules.core.dto.VisitStatus;
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
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@ToString(exclude = "patient")
@NoArgsConstructor
@AllArgsConstructor
class Visit extends AbstractEntity {

  private LocalDateTime date;
  @Enumerated(EnumType.STRING)
  private VisitPaymentStatus paymentStatus;
  @Enumerated(EnumType.STRING)
  private VisitStatus status;
  private boolean reminderSent;
  @ManyToOne
  private Patient patient;

  VisitDTO toDTO() {
	return VisitDTO.builder()
		.id(getId())
		.date(date)
		.paymentStatus(paymentStatus)
		.status(status)
		.reminderSent(reminderSent)
		.patient(patient.toDTO())
		.build();
  }

  static Visit of(VisitDTO visitDTO) {
	return Visit.builder()
		.date(visitDTO.getDate())
		.paymentStatus(AWAITING_PAYMENT)
		.status(AWAITING)
		.reminderSent(false)
		.build();
  }
}
