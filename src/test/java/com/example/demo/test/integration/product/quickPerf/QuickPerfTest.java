package com.example.demo.test.integration.product.quickPerf;

import com.example.demo.test.integration.product.TestData;
import com.example.demo.test.integration.product.data.Product;
import com.example.demo.test.integration.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.quickperf.sql.annotation.ExpectDelete;
import org.quickperf.sql.annotation.ExpectInsert;
import org.quickperf.sql.annotation.ExpectSelect;
import org.quickperf.sql.annotation.ExpectUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@org.quickperf.junit5.QuickPerfTest
class QuickPerfTest {

  @Autowired
  ProductService productService;

  @Test
  @ExpectInsert(1)
  @ExpectSelect(0)
  @ExpectUpdate(0)
  @ExpectDelete(0)
  void shouldSaveProduct() {
	Product response = productService.save(TestData.getSampleProduct());
  }
}