package com.wexinc.purchase.api.dto;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExchangeRateDTOTest {

  private static final ExchangeRateDTO ACTUAL = new ExchangeRateDTO(List.of());

  @Test
  void testData() {
    Assertions.assertEquals(
        List.of(), ExchangeRateDTOTest.ACTUAL.data(), CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
