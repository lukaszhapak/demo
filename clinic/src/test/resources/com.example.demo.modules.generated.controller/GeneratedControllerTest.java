import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.example.demo.modules.generated.controller.GeneratedController;
import com.example.demo.modules.generated.dto.GeneratedDto;
import com.example.demo.modules.generated.entity.Generated;
import com.example.demo.modules.generated.mapper.EntityMapper;
import com.example.demo.modules.generated.mapper.GeneratedMapper;
import com.example.demo.modules.generated.service.GeneratedService;
import java.util.Arrays;
import java.util.Collections;
import org.hamcrest.Matchers;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GeneratedControllerTest {

  private static final String ENDPOINT_URL = "/api/generated";
  @InjectMocks
  private GeneratedController generatedController;
  @Mock
  private GeneratedService generatedService;
  private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
	MockitoAnnotations.initMocks(this);
	mockMvc = MockMvcBuilders
		.standaloneSetup(generatedController)
		//.setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
		//.addFilter(CustomFilter::doFilter)
		.build();
  }

  @Test
  public void findAllByPage() throws Exception {
	Page<GeneratedDto> page = new PageImpl<>(Collections.singletonList(GeneratedBuilder.getDto()));

	Mockito.when(generatedService.findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(page);

	ResultActions resultActions = mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL)
			.accept(MediaType.APPLICATION_JSON))
		.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.data.content", Matchers.hasSize(1)));

	Mockito.verify(generatedService, Mockito.times(1)).findByCondition(ArgumentMatchers.any(), ArgumentMatchers.any());
	Mockito.verifyNoMoreInteractions(generatedService);

  }

  @Test
  public void getById() throws Exception {
	Mockito.when(generatedService.findById(ArgumentMatchers.anyLong())).thenReturn(GeneratedBuilder.getDto());

	mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT_URL + "/1"))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content()
			.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Is.is(1)));
	Mockito.verify(generatedService, Mockito.times(1)).findById("1");
	Mockito.verifyNoMoreInteractions(generatedService);
  }

  @Test
  public void save() throws Exception {
	Mockito.when(generatedService.save(ArgumentMatchers.any(GeneratedDto.class))).thenReturn(GeneratedBuilder.getDto());

	mockMvc.perform(
			MockMvcRequestBuilders.post(ENDPOINT_URL)
				.contentType(MediaType.APPLICATION_JSON)
				.content(CustomUtils.asJsonString(GeneratedBuilder.getDto())))
		.andExpect(MockMvcResultMatchers.status().isCreated());
	Mockito.verify(generatedService, Mockito.times(1)).save(ArgumentMatchers.any(GeneratedDto.class));
	Mockito.verifyNoMoreInteractions(generatedService);
  }

  @Test
  public void update() throws Exception {
	Mockito.when(generatedService.update(ArgumentMatchers.any(), ArgumentMatchers.anyLong())).thenReturn(GeneratedBuilder.getDto());

	mockMvc.perform(
			MockMvcRequestBuilders.put(ENDPOINT_URL + "/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(CustomUtils.asJsonString(GeneratedBuilder.getDto())))
		.andExpect(MockMvcResultMatchers.status().isOk());
	Mockito.verify(generatedService, Mockito.times(1)).update(ArgumentMatchers.any(GeneratedDto.class), ArgumentMatchers.anyLong());
	Mockito.verifyNoMoreInteractions(generatedService);
  }

  @Test
  public void delete() throws Exception {
	Mockito.doNothing().when(generatedService).deleteById(ArgumentMatchers.anyLong());
	mockMvc.perform(
		MockMvcRequestBuilders.delete(ENDPOINT_URL + "/1")
			.contentType(MediaType.APPLICATION_JSON)
			.content(CustomUtils.asJsonString(GeneratedBuilder.getIds()))).andExpect(MockMvcResultMatchers.status().isOk());
	Mockito.verify(generatedService, Mockito.times(1)).deleteById(Mockito.anyLong());
	Mockito.verifyNoMoreInteractions(generatedService);
  }
}