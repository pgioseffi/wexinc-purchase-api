package com.wexinc.purchase.api.resource;

import com.wexinc.purchase.api.shared.constant.Constantes;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExchangeRateResourceTest {

  private static final ExchangeRateResource ACTUAL = new ExchangeRateResource(List.of());

  @Test
  void testData() {
    Assertions.assertEquals(
        List.of(), ExchangeRateResourceTest.ACTUAL.data(), Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
