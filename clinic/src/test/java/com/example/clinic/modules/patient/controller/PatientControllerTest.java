package com.example.clinic.modules.patient.controller;

import static com.example.clinic.commons.helper.TestUtils.getPatientDTO;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.clinic.modules.patient.domain.Patient;
import com.example.clinic.modules.patient.repository.PatientRepositoryInMemory;
import com.example.clinic.modules.patient.service.PatientService;
import com.example.clinic.modules.patient.validator.PatientValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

@DisplayName("Patient controller test")
class PatientControllerTest {

  private final PatientRepositoryInMemory patientRepository = new PatientRepositoryInMemory();
  private final PatientController patientController = new PatientController(new PatientService(patientRepository, new PatientValidator()));

  private final Long EXISTING_PATIENT_ID = 1_000_001L;

  @BeforeEach
  void setUp() {
	patientRepository.cleanData();
	patientRepository.insertData();
  }

  @DisplayName("save tests")
  @Nested
  class SaveTests {

	@Test
	@DisplayName("should save valid patient")
	void shouldSaveValidPatient() {
	  // given
	  PatientDTO request = getPatientDTO();

	  // when
	  PatientDTO response = patientController.save(request);

	  // then
	  assertThat(response.getId()).isNotNull();
	  assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);

	  Patient patient = patientRepository.getById(response.getId());
	  assertThat(patient).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);
	}
  }

  @DisplayName("get tests")
  @Nested
  class GetTests {

	@Test
	@DisplayName("should get patient")
	void shouldGetPatient() {
	  // given
	  Long id = EXISTING_PATIENT_ID;

	  // when
	  PatientDTO response = patientController.findById(id);

	  // then
	  assertThat(response.getId()).isNotNull();
	  assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(getPatientDTO());
	}
  }

  @DisplayName("update tests")
  @Nested
  class UpdateTests {

	@Test
	@DisplayName("should update patient")
	void shouldUpdatePatient() {
	  // given
	  Long id = EXISTING_PATIENT_ID;
	  PatientDTO request = getPatientDTO();
	  request.setId(id);
	  request.setFirstName("Jimmy");
	  request.setLastname("Newman");
	  request.setPhoneNumber("789641615");

	  // when
	  PatientDTO response = patientController.update(id, request);

	  // then
	  assertThat(response).usingRecursiveComparison().isEqualTo(request);

	  Patient patient = patientRepository.getById(response.getId());
	  assertThat(patient).usingRecursiveComparison().isEqualTo(request);
	}

	@DisplayName("delete tests")
	@Nested
	class DeleteTests {

	  @Test
	  @DisplayName("should delete student")
	  void shouldDeleteStudent() {
		// given
		Long id = EXISTING_PATIENT_ID;

		// when
		patientController.delete(id);

		// then
		assertThat(patientRepository.existsById(id)).isFalse();
	  }
	}
  }
}