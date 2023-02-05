package integration;

import static com.example.clinic.commons.TestUtils.getPatient;
import static javax.servlet.http.HttpServletResponse.SC_OK;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.clinic.commons.AbstractIntegrationTest;
import com.example.clinic.commons.JdbcTestHelper;
import com.example.clinic.modules.patient.controller.PatientDTO;
import com.example.clinic.modules.patient.domain.Patient;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PatientControllerIntegrationTest extends AbstractIntegrationTest {

  public static final long EXISTING_PATIENT_ID = 1000001L;
  private final String URL = "/clinic/api/patient/";

  @Autowired
  private JdbcTestHelper<Patient> jdbcTestHelper;

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
	  Response response = postHttpCall(request, URL);
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
  @DisplayName("get tests")
  class GetTests {

	@Test
	@DisplayName("should get patient")
	void shouldGetPatient() {
	  // given
	  Patient patient = getPatient();

	  // when
	  Response response = getHttpCall(URL + "1000001");
	  PatientDTO responseAsPatient = response.as(PatientDTO.class);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_OK);

	  assertThat(responseAsPatient.getId()).isNotNull();
	  assertThat(responseAsPatient).usingRecursiveComparison().ignoringFields("id").isEqualTo(patient);
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
	  request.setId(EXISTING_PATIENT_ID);
	  request.setFirstName("Jimmy");
	  request.setLastName("Newman");
	  request.setPhoneNumber("789641615");

	  // when
	  Response response = putHttpCall(request, URL + "1000001");
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
	@DisplayName("should delete student")
	void shouldDeleteStudent() {
	  // given
	  Long id = EXISTING_PATIENT_ID;

	  // when
	  Response response = deleteHttpCall(URL + id);

	  // then
	  assertThat(response.statusCode()).isEqualTo(SC_OK);

	  assertThat(jdbcTestHelper.existsById("PATIENT", id)).isFalse();
	}
  }
}