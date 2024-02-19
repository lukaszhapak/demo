package com.example.clinic.modules.core.domain;

import static com.example.clinic.commons.TestUtils.getPatientDTO;
import static com.example.clinic.commons.TestUtils.getVisitDTO;
import static com.example.clinic.modules.core.dto.VisitPaymentStatus.AWAITING_PAYMENT;
import static com.example.clinic.modules.core.dto.VisitStatus.AWAITING;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.clinic.modules.core.dto.PatientDTO;
import com.example.clinic.modules.core.dto.VisitDTO;
import com.example.commons.exception.NotFoundException;
import com.example.commons.exception.ValidationException;
import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Visit facade test")
public class VisitFacadeTest {

  private final PatientFacade patientFacade = new CoreConfiguration().patientFacade(new PatientRepositoryInMemory());
  private final VisitFacade visitFacade = new CoreConfiguration().visitFacade(new VisitRepositoryInMemory(), patientFacade);

  private static final long NON_EXISTING_PATIENT_ID = 1000000L;
  private static final long NON_EXISTING_VISIT_ID = 2000000L;

  @Nested
  @DisplayName("save tests")
  class SaveTests {

	@Test
	@DisplayName("should save valid visit")
	void shouldSaveValidVisit() {
	  // given
	  VisitDTO request = getVisitDTO();
	  PatientDTO savedPatient = patientFacade.save(getPatientDTO());

	  // when
	  VisitDTO response = visitFacade.save(savedPatient.getId(), request);

	  // then
	  assertThat(response.getId()).isNotNull();
	  assertThat(response).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(request);

	  VisitDTO visitInDb = visitFacade.findById(response.getId());
	  assertThat(visitInDb).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(request);
	  assertThat(visitInDb.getStatus()).isEqualTo(AWAITING);
	  assertThat(visitInDb.getPaymentStatus()).isEqualTo(AWAITING_PAYMENT);
	  assertThat(visitInDb.isReminderSent()).isFalse();
	  assertThat(visitInDb.getPatient().getId()).isEqualTo(savedPatient.getId());
	}

	@Test
	@DisplayName("should not save visit with past date")
	void shouldNotSaveVisitWithPastDate() {
	  // given
	  VisitDTO request = getVisitDTO();
	  request.setDate(LocalDateTime.now().minusDays(1));
	  PatientDTO savedPatient = patientFacade.save(getPatientDTO());

	  // when
	  ValidationException thrown = (ValidationException) catchThrowable(() -> visitFacade.save(savedPatient.getId(), request));

	  // then
	  assertThat(thrown.getInvalidFields()).hasSize(1)
		  .containsKeys("date");
	}

	@Test
	@DisplayName("should not save visit for non existing patient")
	void shouldNotSaveVisitForNonExisting() {
	  // given
	  VisitDTO request = getVisitDTO();

	  // when
	  Throwable thrown = catchThrowable(() -> visitFacade.save(NON_EXISTING_PATIENT_ID, request));

	  // then
	  assertThat(thrown).isInstanceOf(NotFoundException.class);
	}
  }

  @Nested
  @DisplayName("save tests")
  class GetTests {

	@Test
	@DisplayName("should get visit")
	void shouldGetVisit() {
	  // given
	  PatientDTO savedPatient = patientFacade.save(getPatientDTO());
	  VisitDTO savedVisit = visitFacade.save(savedPatient.getId(), getVisitDTO());

	  // when
	  VisitDTO response = visitFacade.findById(savedVisit.getId());

	  // then
	  assertThat(response).usingRecursiveComparison().isEqualTo(savedVisit);
	}

	@Test
	@DisplayName("should not get not existing visit")
	void shouldNotGetNotExistingVisit() {
	  // given
	  Long id = 100L;

	  // when
	  Throwable thrown = catchThrowable(() -> visitFacade.findById(id));

	  // then
	  assertThat(thrown).isInstanceOf(NotFoundException.class);
	}
  }
}