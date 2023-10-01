package com.example.demo.spring.context;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TestContext.class)
//@SpringBootTest(classes = TestContext.class)
class ContextTest {

  @Autowired
  ServiceForTestContext serviceForTestContext;

  @Autowired
  StudentRepository studentRepository;

  @Test
  @DisplayName("Test name")
  void testName() {
	// given
    Stuudent stuudent = new Stuudent();

	// when
    serviceForTestContext.runSomeMethod();


    List<Stuudent> all = studentRepository.findAll();
    System.out.println(all.size());


    studentRepository.save(stuudent);

    all = studentRepository.findAll();
    System.out.println(all.size());

    // then
  }
}
