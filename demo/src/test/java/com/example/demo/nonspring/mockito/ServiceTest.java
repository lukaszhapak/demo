package com.example.demo.nonspring.mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

@Slf4j
class ServiceTest {

  private final Service serviceMock = Mockito.mock(Service.class);
  private final Service serviceSpy = Mockito.spy(Service.class);

  @Test
  @DisplayName("throw exception")
  void throwException() {
	// given
	when(serviceMock.returningInt()).thenThrow(new RuntimeException());
	doThrow(new RuntimeException()).when(serviceMock).returningVoid();

	// when
	Throwable thrown = catchThrowable(serviceMock::returningVoid);
	Throwable thrown2 = catchThrowable(serviceMock::returningInt);
  }

  @Test
  @DisplayName("call real method")
  void callRealMethod() {
	// given
	when(serviceSpy.returningInt()).thenCallRealMethod();
	doCallRealMethod().when(serviceSpy).returningVoid();
	when(serviceMock.returningInt()).thenCallRealMethod();
	doCallRealMethod().when(serviceMock).returningVoid();

	// when
	int mockResult = serviceMock.returningInt();
	serviceMock.returningVoid();
	int spyResult = serviceSpy.returningInt();
	serviceSpy.returningVoid();

	assertThat(mockResult).isEqualTo(5);
	assertThat(spyResult).isEqualTo(5);
  }

  @Test
  @DisplayName("do nothing")
  void doNothingTest() {
	// given
	doNothing().when(serviceSpy).returningVoid();
	doNothing().when(serviceMock).returningVoid();

	// when
	int mockResult = serviceMock.returningInt();
	serviceMock.returningVoid();
	int spyResult = serviceSpy.returningInt();
	serviceSpy.returningVoid();

	// then
	assertThat(mockResult).isEqualTo(0);
	assertThat(spyResult).isEqualTo(5);
  }

  @Test
  @DisplayName("stubbing with callback")
  void stubbingWithCallback() {
	// given
	when(serviceMock.returningInt(any())).thenAnswer(invocation -> testReturningInt(invocation.getArgument(0)));

	// when
	int result = serviceMock.returningInt(3);

	// then
	assertThat(result).isEqualTo(6);
  }

  @Test
  @DisplayName("spy with dependency")
  void spyWithDependency() {
	// given
	Repository repository = new Repository();
	ServiceWithDependency service = spy(new ServiceWithDependency(repository));

	// when
	int result = service.returningIntFromRepository();

	// then
	assertThat(result).isEqualTo(255);
  }

  private int testReturningInt(Integer number) {
	log.debug("returning int, number={}", number + 3);
	return number + 3;
  }
}