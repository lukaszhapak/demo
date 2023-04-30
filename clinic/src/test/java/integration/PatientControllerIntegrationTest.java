package integration;

import static com.example.clinic.commons.TestUtils.getPatient;
import static com.example.clinic.commons.TestUtils.getPatientDTO;
import static javax.servlet.http.HttpServletResponse.SC_BAD_REQUEST;
import static javax.servlet.http.HttpServletResponse.SC_NOT_FOUND;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.clinic.commons.ClinicAbstractIntegrationTest;
import com.example.clinic.modules.core.patient.model.Patient;
import com.example.clinic.modules.core.patient.model.PatientDTO;
import com.example.commons.exception.ValidationExceptionDTO;
import com.example.commons.test.JdbcTestHelper;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class PatientControllerIntegrationTest extends ClinicAbstractIntegrationTest {

  private static final long GET_PATIENT_ID = 1000001L;
  private static final long UPDATE_PATIENT_ID = 1000002L;
  private static final long DELETE_PATIENT_ID = 1000003L;
  private static final long NON_EXISTING_PATIENT_ID = 9999999L;

  private final String URL = "/api/patient/";

  @Autowired
  protected JdbcTestHelper<Patient> jdbcTestHelper;

  @Nested
  @DisplayName("save tests")
  class SaveTests {

	@Test
	@DisplayName("should save valid patient")
	void shouldSaveValidPatient() {
	  // given
	  Patient request = getPatient();
	  request.setPesel("93745637618");

	  // when
	  Response response = postHttpCall(request, URL, port);
	  PatientDTO responseAsPatient = response.as(PatientDTO.class);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_OK);

	  assertThat(responseAsPatient.getId()).isNotNull();
	  assertThat(responseAsPatient).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);

	  Patient patient = jdbcTestHelper.fetchEntity("Patient", responseAsPatient.getId(), Patient.class);
	  assertThat(patient).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);
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
	  Response response = postHttpCall(request, URL, port);
	  ValidationExceptionDTO responseAsException = response.as(ValidationExceptionDTO.class);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_BAD_REQUEST);
	  assertThat(responseAsException.getInvalidFields()).hasSize(4)
		  .containsKeys(expectedInvalidFields);
	}
  }

  @Nested
  @DisplayName("get tests")
  class GetTests {

	@Test
	@DisplayName("should get patient")
	void shouldGetPatient() {
	  // given
	  Patient patient = getPatient();

	  // when
	  Response response = getHttpCall(URL + GET_PATIENT_ID, port);
	  PatientDTO responseAsPatient = response.as(PatientDTO.class);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_OK);

	  assertThat(responseAsPatient.getId()).isNotNull();
	  assertThat(responseAsPatient).usingRecursiveComparison().ignoringFields("id").isEqualTo(patient);
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
	  Patient request = getPatient();
	  request.setId(UPDATE_PATIENT_ID);
	  request.setFirstName("Jimmy");
	  request.setLastName("Newman");
	  request.setPesel("98654222942");
	  request.setPhoneNumber("789641615");

	  // when
	  Response response = putHttpCall(request, URL + request.getId(), port);
	  PatientDTO responseAsPatient = response.as(PatientDTO.class);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_OK);

	  assertThat(responseAsPatient.getId()).isNotNull();
	  assertThat(responseAsPatient).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);

	  Patient patient = jdbcTestHelper.fetchEntity("Patient", responseAsPatient.getId(), Patient.class);
	  assertThat(patient).usingRecursiveComparison().ignoringFields("id").isEqualTo(request);
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

	  assertThat(jdbcTestHelper.existsById("PATIENT", id)).isFalse();
	}
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
