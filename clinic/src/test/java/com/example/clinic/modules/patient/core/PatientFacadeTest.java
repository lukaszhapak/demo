package com.example.clinic.modules.patient.core;

import static com.example.clinic.commons.TestUtils.getPatient;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.clinic.commons.exception.NotFoundException;
import com.example.clinic.config.ClinicConfiguration;
import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.repository.PatientRepositoryInMemory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Patient facade test")
public class PatientFacadeTest {

  private final PatientFacade patientFacade = new ClinicConfiguration().patientFacade(new PatientRepositoryInMemory());

  @Nested
  @DisplayName("save tests")
  class SaveTests {

	@Test
	@DisplayName("should save valid patient")
	void shouldSaveValidPatient() {
	  // given
	  Patient request = getPatient();

	  // when
	  Patient response = patientFacade.save(request);

	  // then
	  assertThat(response.getId()).isNotNull();
	  assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);

	  Patient patient = patientFacade.findById(response.getId());
	  assertThat(patient).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);
	}
  }

  @Nested
  @DisplayName("get tests")
  class GetTests {

	@Test
	@DisplayName("should get patient")
	void shouldGetPatient() {
	  // given
	  Patient patient = patientFacade.save(getPatient());

	  // when
	  Patient response = patientFacade.findById(patient.getId());

	  // then
	  assertThat(response.getId()).isEqualTo(patient.getId());
	  assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(patient);
	}
  }

  @Nested
  @DisplayName("update tests")
  class UpdateTests {

	@Test
	@DisplayName("should update patient")
	void shouldUpdatePatient() {
	  // given
	  Patient patient = patientFacade.save(getPatient());
	  Patient request = getPatient();
	  request.setId(patient.getId());
	  request.setFirstName("Jimmy");
	  request.setLastName("Newman");
	  request.setPhoneNumber("789641615");

	  // when
	  Patient response = patientFacade.update(patient.getId(), request);

	  // then
	  assertThat(response).usingRecursiveComparison().isEqualTo(request);

	  Patient patientInDb = patientFacade.findById(response.getId());
	  assertThat(patientInDb).usingRecursiveComparison().isEqualTo(request);
	}
  }

  @Nested
  @DisplayName("delete tests")
  class DeleteTests {

	@Test
	@DisplayName("should delete student")
	void shouldDeleteStudent() {
	  // given
	  Patient patient = patientFacade.save(getPatient());

	  // when
	  patientFacade.deleteById(patient.getId());

	  // then
	  Throwable thrown = catchThrowable(() -> patientFacade.findById(patient.getId()));
	  assertThat(thrown).isInstanceOf(NotFoundException.class);
	}
  }
}