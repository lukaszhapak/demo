package com.example.demo.nonspring.mockito;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
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
	serviceMock.returningInt();
	serviceMock.returningVoid();
	serviceSpy.returningInt();
	serviceSpy.returningVoid();
  }

  @Test
  @DisplayName("do nothing")
  void doNothingTest() {
	// given
	doNothing().when(serviceSpy).returningVoid();
	doNothing().when(serviceMock).returningVoid();

	// when
	serviceMock.returningInt();
	serviceMock.returningVoid();
	serviceSpy.returningInt();
	serviceSpy.returningVoid();
  }

  @Test
  @DisplayName("stubbing with callback")
  void stubbingWithCallback() {
	// given
	when(serviceMock.returningInt(any())).thenAnswer(invocation -> testReturningInt(invocation.getArgument(0)));

	// when
	serviceMock.returningInt(3);
  }

  private int testReturningInt(Integer number) {
	log.debug("returning int, number={}", number + 3);
	return number + 3;
  }
}