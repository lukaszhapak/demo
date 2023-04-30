package com.example.clinic.modules.core.patient.service;

import static com.example.clinic.commons.TestUtils.getPatientDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.clinic.config.ClinicConfiguration;
import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.clinic.modules.core.patient.repository.PatientRepositoryInMemory;
import com.example.commons.exception.NotFoundException;
import com.example.commons.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

	@Test
	@DisplayName("should not save patient with invalid fields")
	void shouldNotSavePatientWithInvalidFields() {
	  // given
	  PatientDTO request = getPatientDTO();
	  request.setFirstName("J");
	  request.setLastName("N");
	  request.setPesel("3");
	  request.setPhoneNumber("7896416");
	  String[] expectedInvalidFields = {"firstName", "lastName", "pesel", "phoneNumber"};

	  // when
	  ValidationException thrown = (ValidationException) catchThrowable(() -> patientFacade.save(request));

	  // then
	  assertThat(thrown).isInstanceOf(ValidationException.class);
	  assertThat(thrown.getInvalidFields()).hasSize(4)
		  .containsKeys(expectedInvalidFields);
	}

	@ParameterizedTest
	@ValueSource(strings = {"a", "0", "this string has more than 64 characters--------------------------"})
	@DisplayName("should not save patient with invalid first name")
	void shouldNotSavePatientWithInvalidFirstName(String firstName) {
	  // given
	  PatientDTO request = getPatientDTO();
	  request.setFirstName(firstName);

	  // when
	  ValidationException thrown = (ValidationException) catchThrowable(() -> patientFacade.save(request));

	  // then
	  assertThat(thrown).isInstanceOf(ValidationException.class);
	  assertThat(thrown.getInvalidFields()).hasSize(1)
		  .containsKeys("firstName");
	}

	@ParameterizedTest
	@ValueSource(strings = {"a", "0", "this string has more than 64 characters--------------------------"})
	@DisplayName("should not save patient with invalid last name")
	void shouldNotSavePatientWithInvalidLastName(String lastName) {
	  // given
	  PatientDTO request = getPatientDTO();
	  request.setLastName(lastName);

	  // when
	  ValidationException thrown = (ValidationException) catchThrowable(() -> patientFacade.save(request));

	  // then
	  assertThat(thrown).isInstanceOf(ValidationException.class);
	  assertThat(thrown.getInvalidFields()).hasSize(1)
		  .containsKeys("lastName");
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "5char", "10-chars--", "12-chars----", "20-characters-------"})
	@DisplayName("should not save patient with invalid pesel")
	void shouldNotSavePatientWithInvalidPesel(String pesel) {
	  // given
	  PatientDTO request = getPatientDTO();
	  request.setPesel(pesel);

	  // when
	  ValidationException thrown = (ValidationException) catchThrowable(() -> patientFacade.save(request));

	  // then
	  assertThat(thrown).isInstanceOf(ValidationException.class);
	  assertThat(thrown.getInvalidFields()).hasSize(1)
		  .containsKeys("pesel");
	}

	@ParameterizedTest
	@ValueSource(strings = {"1", "5char","8--chars", "10-chars--", "12-chars----", "20-characters-------"})
	@DisplayName("should not save patient with invalid phone number")
	void shouldNotSavePatientWithInvalidPhoneNumber(String phoneNumber) {
	  // given
	  PatientDTO request = getPatientDTO();
	  request.setPhoneNumber(phoneNumber);

	  // when
	  ValidationException thrown = (ValidationException) catchThrowable(() -> patientFacade.save(request));

	  // then
	  assertThat(thrown).isInstanceOf(ValidationException.class);
	  assertThat(thrown.getInvalidFields()).hasSize(1)
		  .containsKeys("phoneNumber");
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
	  assertThat(response).usingRecursiveComparison().isEqualTo(patient);
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