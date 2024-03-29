package com.example.demo.test.basics.mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import com.example.demo.test.basics.NumberRepository;
import com.example.demo.test.basics.NumberService;
import com.example.demo.test.basics.NumberServiceWithDependency;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Slf4j
class MockVsSpyTest {

  final NumberService numberServiceMock = mock(NumberService.class);
  final NumberService numberServiceSpy = spy(NumberService.class);

  @Test
  @DisplayName("throw exception")
  void throwException() {
	// given
	when(numberServiceMock.returningInt()).thenThrow(new RuntimeException());
	doThrow(new RuntimeException()).when(numberServiceMock).returningVoid();

	when(numberServiceSpy.returningInt()).thenThrow(new RuntimeException());
	doThrow(new RuntimeException()).when(numberServiceSpy).returningVoid();

	// when
	Throwable thrown = catchThrowable(numberServiceMock::returningVoid);

	assertThat(thrown).isInstanceOf(RuntimeException.class);
	assertThat(catchThrowable(numberServiceMock::returningInt)).isInstanceOf(RuntimeException.class);
	assertThat(catchThrowable(numberServiceSpy::returningVoid)).isInstanceOf(RuntimeException.class);
	assertThat(catchThrowable(numberServiceSpy::returningInt)).isInstanceOf(RuntimeException.class);
  }

  @Test
  @DisplayName("call real method")
  void callRealMethod() {
	// given
	when(numberServiceSpy.returningInt()).thenCallRealMethod();
	doCallRealMethod().when(numberServiceSpy).returningVoid();

	when(numberServiceMock.returningInt()).thenCallRealMethod();
	doCallRealMethod().when(numberServiceMock).returningVoid();

	// when
	int mockResult = numberServiceMock.returningInt();
	numberServiceMock.returningVoid();

	int spyResult = numberServiceSpy.returningInt();
	numberServiceSpy.returningVoid();

	assertThat(mockResult).isEqualTo(5);
	assertThat(spyResult).isEqualTo(5);
  }

  @Test
  @DisplayName("do nothing")
  void doNothingTest() {
	// given
	doNothing().when(numberServiceMock).returningVoid();
	doNothing().when(numberServiceSpy).returningVoid();
	// Only void methods can doNothing()

	// when
	int mockResult = numberServiceMock.returningInt();
	numberServiceMock.returningVoid();

	int spyResult = numberServiceSpy.returningInt();
	numberServiceSpy.returningVoid();

	// then
	assertThat(mockResult).isEqualTo(0);
	assertThat(spyResult).isEqualTo(5);
  }

  @Test
  @DisplayName("spy with dependency")
  void spyWithDependency() {
	// given
	NumberRepository numberRepository = new NumberRepository();
	NumberServiceWithDependency service = spy(new NumberServiceWithDependency(numberRepository));
	// cannot do that with mock

	// when
	int result = service.returningIntFromRepository();

	// then
	assertThat(result).isEqualTo(255);
  }

  @Test
  @DisplayName("stubbing with callback")
  void stubbingWithCallback() {
	// given
	when(numberServiceMock.returningInt(any())).thenAnswer(invocation -> testReturningInt(invocation.getArgument(0)));
	// cannot do that with spy

	// when
	int result = numberServiceMock.returningInt(3);

	// then
	assertThat(result).isEqualTo(6);
  }

  int testReturningInt(Integer number) {
	log.debug("returning int, number={}", number + 3);
	return number + 3;
  }
}