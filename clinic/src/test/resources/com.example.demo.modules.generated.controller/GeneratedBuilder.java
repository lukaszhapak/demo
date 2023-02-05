import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;

public class GeneratedBuilder {

  public static List<String> getIds() {
	return Collections.singletonList("1");
  }

  public static GeneratedDto getDto() {
	GeneratedDto dto = new GeneratedDto();
	dto.setId("1");
	return dto;
  }
}