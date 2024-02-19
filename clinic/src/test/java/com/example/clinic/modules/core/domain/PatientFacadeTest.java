package com.example.clinic.modules.core.domain;

import static com.example.clinic.commons.TestUtils.getPatientDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.clinic.modules.core.dto.PatientDTO;
import com.example.commons.exception.NotFoundException;
import com.example.commons.exception.ValidationException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("Patient facade test")
public class PatientFacadeTest {

  private final PatientFacade patientFacade = new CoreConfiguration().patientFacade(new PatientRepositoryInMemory());

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
	  assertThat(response).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(request);

	  PatientDTO patientInDb = patientFacade.findById(response.getId());
	  assertThat(patientInDb).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(request);
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
	@ValueSource(strings = {"1", "5char", "8--chars", "10-chars--", "12-chars----", "20-characters-------"})
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

	@Test
	@DisplayName("should not save patient with duplicated pesel")
	void shouldNotSavePatientWithDuplicatedPesel() {
	  // given
	  PatientDTO request = getPatientDTO();
	  patientFacade.save(request);

	  // when
	  ValidationException thrown = (ValidationException) catchThrowable(() -> patientFacade.save(request));

	  // then
	  assertThat(thrown.getInvalidFields()).hasSize(1)
		  .containsKeys("pesel");
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

	@Test
	@DisplayName("should not get non existing patient")
	void shouldNotGetNonExistingPatient() {
	  // given
	  Long id = 100L;

	  // when
	  Throwable thrown = catchThrowable(() -> patientFacade.findById(id));

	  // then
	  assertThat(thrown).isInstanceOf(NotFoundException.class)
		  .hasMessageStartingWith("Patient");
	}
  }

  @Nested
  @DisplayName("update tests")
  class UpdateTests {

	@Test
	@DisplayName("should update patient")
	void shouldUpdatePatient() {
	  // given
	  Long id = patientFacade.save(getPatientDTO()).getId();
	  PatientDTO request = getPatientDTO();
	  request.setFirstName("Jimmy");
	  request.setLastName("Newman");
	  request.setPhoneNumber("789641615");

	  // when
	  PatientDTO response = patientFacade.update(id, request);

	  // then
	  assertThat(response).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(request);

	  PatientDTO patientInDb = patientFacade.findById(response.getId());
	  assertThat(patientInDb).usingRecursiveComparison().ignoringExpectedNullFields().isEqualTo(request);
	}

	@Test
	@DisplayName("should not update not existing patient")
	void shouldNotUpdateNotExistingPatient() {
	  // given
	  PatientDTO request = getPatientDTO();

	  Throwable thrown = catchThrowable(() -> patientFacade.update(100L, request));

	  // then
	  assertThat(thrown).isInstanceOf(NotFoundException.class)
		  .hasMessageStartingWith("Patient");
	}

	@Test
	@DisplayName("should not update patient with invalid fields")
	void shouldNotUpdatePatientWithInvalidFields() {
	  // given
	  Long id = patientFacade.save(getPatientDTO()).getId();
	  PatientDTO request = getPatientDTO();
	  request.setFirstName("J");
	  request.setLastName("N");
	  request.setPesel("3");
	  request.setPhoneNumber("7896416");
	  String[] expectedInvalidFields = {"firstName", "lastName", "pesel", "phoneNumber"};

	  // when
	  ValidationException thrown = (ValidationException) catchThrowable(() -> patientFacade.update(id, request));

	  // then
	  assertThat(thrown.getInvalidFields()).hasSize(4)
		  .containsKeys(expectedInvalidFields);
	}
  }

  @Nested
  @DisplayName("delete tests")
  class DeleteTests {

	@Test
	@DisplayName("should delete patient")
	void shouldDeletePatient() {
	  // given
	  Long id = patientFacade.save(getPatientDTO()).getId();

	  // when
	  patientFacade.deleteById(id);

	  // then
	  Throwable thrown = catchThrowable(() -> patientFacade.findById(id));
	  assertThat(thrown).isInstanceOf(NotFoundException.class)
		  .hasMessageStartingWith("Patient");
	}

	@Test
	@DisplayName("should not delete non existing patient")
	void shouldNotDeleteNonExistingPatient() {
	  // given
	  Long id = 100L;

	  // when
	  Throwable thrown = catchThrowable(() -> patientFacade.deleteById(id));

	  // then
	  assertThat(thrown).isInstanceOf(NotFoundException.class)
		  .hasMessageStartingWith("Patient");
	}
  }
}