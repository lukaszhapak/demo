package com.example.clinic.modules.core.patient.service;

import static com.example.clinic.commons.TestUtils.getPatientDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.clinic.exception.NotFoundException;
import com.example.clinic.config.ClinicConfiguration;
import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.clinic.modules.core.patient.repository.PatientRepositoryInMemory;
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
	  PatientDTO request = getPatientDTO();

	  // when
	  PatientDTO response = patientFacade.save(request);

	  // then
	  assertThat(response.getId()).isNotNull();
	  assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);

	  PatientDTO patientInDb = patientFacade.findById(response.getId());
	  assertThat(patientInDb).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);
	}
  }

  @Nested
  @DisplayName("get tests")
  class GetTests {

	@Test
	@DisplayName("should get patient")
	void shouldGetPatient() {
	  // given
	  PatientDTO patient = patientFacade.save(getPatientDTO());

	  // when
	  PatientDTO response = patientFacade.findById(patient.getId());

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
	  PatientDTO patient = patientFacade.save(getPatientDTO());
	  PatientDTO request = getPatientDTO();
	  request.setId(patient.getId());
	  request.setFirstName("Jimmy");
	  request.setLastName("Newman");
	  request.setPhoneNumber("789641615");

	  // when
	  PatientDTO response = patientFacade.update(patient.getId(), request);

	  // then
	  assertThat(response).usingRecursiveComparison().isEqualTo(request);

	  PatientDTO patientInDb = patientFacade.findById(response.getId());
	  assertThat(patientInDb).usingRecursiveComparison().isEqualTo(request);
	}
  }

  @Nested
  @DisplayName("delete tests")
  class DeleteTests {

	@Test
	@DisplayName("should delete patient")
	void shouldDeletePatient() {
	  // given
	  PatientDTO patient = patientFacade.save(getPatientDTO());

	  // when
	  patientFacade.deleteById(patient.getId());

	  // then
	  Throwable thrown = catchThrowable(() -> patientFacade.findById(patient.getId()));
	  assertThat(thrown).isInstanceOf(NotFoundException.class);
	}
  }
}