package com.wexinc.purchase.api.resource;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExchangeRateResourceTest {

  private static final ExchangeRateResource ACTUAL = new ExchangeRateResource(List.of());

  @Test
  void testData() {
    Assertions.assertEquals(
        List.of(),
        ExchangeRateResourceTest.ACTUAL.data(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
