package com.example.demo.test.integration.testData;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Rollback(value = false)
@Commit
@SpringBootTest
class TransactionalTest {

}
