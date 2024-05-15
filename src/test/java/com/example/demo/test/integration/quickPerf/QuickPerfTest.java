package com.example.demo.test.integration.quickPerf;

import com.example.demo.test.integration.Product;
import com.example.demo.test.integration.ProductRepository;
import com.example.demo.test.integration.TestData;
import org.junit.jupiter.api.Test;
import org.quickperf.spring.sql.QuickPerfSqlConfig;
import org.quickperf.sql.annotation.ExpectDelete;
import org.quickperf.sql.annotation.ExpectInsert;
import org.quickperf.sql.annotation.ExpectSelect;
import org.quickperf.sql.annotation.ExpectUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@org.quickperf.junit5.QuickPerfTest
@Import({QuickPerfSqlConfig.class})
class QuickPerfTest {

  @Autowired
  ProductRepository productRepository;

  @Test
  @ExpectInsert(1)
  @ExpectSelect(0)
  @ExpectUpdate(0)
  @ExpectDelete(0)
  void shouldSaveProduct() {
	Product response = productRepository.save(TestData.getSampleProduct());
  }
}