package com.example.clinic.modules.core.visit.model;

import com.example.clinic.modules.core.patient.model.PatientDTO;
import java.time.LocalDateTime;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VisitDTO {

  private Long id;
  private LocalDateTime visitDate;
  @Enumerated(EnumType.STRING)
  private VisitPaymentStatus paymentStatus;
  private boolean reminderSent;
  private PatientDTO patient;
}
