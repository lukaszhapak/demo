package integration;

import static com.example.clinic.commons.TestUtils.getPatientDTO;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

import com.example.clinic.commons.ClinicAbstractIntegrationTest;
import com.example.clinic.modules.core.domain.PatientFacade;
import com.example.clinic.modules.core.dto.PatientDTO;
import com.example.commons.exception.NotFoundException;
import com.example.commons.exception.ValidationExceptionDTO;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientControllerIntegrationTest extends ClinicAbstractIntegrationTest {

  private static final long GET_PATIENT_ID = 1000001L;
  private static final long UPDATE_PATIENT_ID = 1000002L;
  private static final long DELETE_PATIENT_ID = 1000003L;
  private static final long NON_EXISTING_PATIENT_ID = 9999999L;

  private final String URL = "/clinic/api/patient/";

  @Autowired
  protected PatientFacade patientFacade;

  @Nested
  @DisplayName("save tests")
  class SaveTests {

	@Test
	@DisplayName("should save valid patient")
	void shouldSaveValidPatient() {
	  // given
	  PatientDTO request = getPatientDTO();
	  request.setPesel("93745637618");

	  // when
	  Response httpResponse = postHttpCall(request, URL, port);
	  PatientDTO response = httpResponse.as(PatientDTO.class);

	  // then
	  assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);
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
	  Response httpResponse = postHttpCall(request, URL, port);
	  ValidationExceptionDTO thrown = httpResponse.as(ValidationExceptionDTO.class);

	  // then
	  assertThat(httpResponse.statusCode()).isEqualTo(SC_BAD_REQUEST);

	  assertThat(thrown.getInvalidFields()).hasSize(4)
		  .containsKeys(expectedInvalidFields);
	}

	@Test
	@DisplayName("should not save patient with duplicated pesel")
	void shouldNotSavePatientWithDuplicatedPesel() {
	  // given
	  PatientDTO request = getPatientDTO();

	  // when
	  Response httpResponse = postHttpCall(request, URL, port);
	  ValidationExceptionDTO thrown = httpResponse.as(ValidationExceptionDTO.class);

	  // then
	  assertThat(httpResponse.statusCode()).isEqualTo(SC_BAD_REQUEST);
	  assertThat(thrown.getInvalidFields()).hasSize(1)
		  .containsKey("pesel");
	}
  }

  @Nested
  @DisplayName("get tests")
  class GetTests {

	@Test
	@DisplayName("should get patient")
	void shouldGetPatient() {
	  // given
	  PatientDTO patient = getPatientDTO();

	  // when
	  Response httpResponse = getHttpCall(URL + GET_PATIENT_ID, port);
	  PatientDTO response = httpResponse.as(PatientDTO.class);

	  // then
	  assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);

	  assertThat(response.getId()).isNotNull();
	  assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(patient);
	}

	@Test
	@DisplayName("should not get non existing patient")
	void shouldNotGetNonExistingPatient() {
	  // given
	  Long id = NON_EXISTING_PATIENT_ID;

	  // when
	  Response response = getHttpCall(URL + id, port);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_NOT_FOUND);
	}
  }

  @Nested
  @DisplayName("update tests")
  class UpdateTests {

	@Test
	@DisplayName("should update patient")
	void shouldUpdatePatient() {
	  // given
	  PatientDTO request = getPatientDTO();
	  request.setFirstName("Jimmy");
	  request.setLastName("Newman");
	  request.setPesel("98654222942");
	  request.setPhoneNumber("789641615");

	  // when
	  Response httpResponse = putHttpCall(request, URL + UPDATE_PATIENT_ID, port);
	  PatientDTO response = httpResponse.as(PatientDTO.class);

	  // then
	  assertThat(httpResponse.statusCode()).isEqualTo(SC_OK);

	  assertThat(response.getId()).isNotNull();
	  assertThat(response).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);

	  PatientDTO patientInDb = patientFacade.findById(response.getId());
	  assertThat(patientInDb).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);
	}

	@Test
	@DisplayName("should not update patient with invalid fields")
	void shouldNotUpdatePatientWithInvalidFields() {
	  // given
	  PatientDTO request = getPatientDTO();
	  request.setFirstName("J");
	  request.setLastName("N");
	  request.setPesel("3");
	  request.setPhoneNumber("7896416");
	  String[] expectedInvalidFields = {"firstName", "lastName", "pesel", "phoneNumber"};

	  // when
	  Response httpResponse = putHttpCall(request, URL + UPDATE_PATIENT_ID, port);
	  ValidationExceptionDTO thrown = httpResponse.as(ValidationExceptionDTO.class);

	  // then
	  assertThat(httpResponse.statusCode()).isEqualTo(SC_BAD_REQUEST);
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
	  Long id = DELETE_PATIENT_ID;

	  // when
	  Response response = deleteHttpCall(URL + id, port);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_OK);

	  Throwable thrown = catchThrowable(() -> patientFacade.findById(id));
	  assertThat(thrown).isInstanceOf(NotFoundException.class);
	}

	@Test
	@DisplayName("should not delete non existing patient")
	void shouldNotDeleteNonExistingPatient() {
	  // given
	  Long id = NON_EXISTING_PATIENT_ID;

	  // when
	  Response response = deleteHttpCall(URL + id, port);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_NOT_FOUND);
	}
  }
}
