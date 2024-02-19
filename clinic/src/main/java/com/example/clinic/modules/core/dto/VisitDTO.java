package com.example.clinic.modules.core.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class VisitDTO {

  private Long id;
  private LocalDateTime date;
  private VisitPaymentStatus paymentStatus;
  private VisitStatus status;
  private boolean reminderSent;
  private PatientDTO patient;
}
