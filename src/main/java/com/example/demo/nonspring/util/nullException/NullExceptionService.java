package com.example.demo.nonspring.util.nullException;

class NullExceptionService {

  void throwNullException() throws Exception {
	Exception e = null;
	throw e;
  }
}
