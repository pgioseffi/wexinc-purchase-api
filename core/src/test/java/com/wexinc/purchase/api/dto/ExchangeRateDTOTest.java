package com.wexinc.purchase.api.dto;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateDataDTOFixture;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExchangeRateDTOTest {

  private static final ExchangeRateDTO ACTUAL =
      new ExchangeRateDTO(List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE));

  @Test
  void testData() {
    Assertions.assertIterableEquals(
        List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE),
        ExchangeRateDTOTest.ACTUAL.data(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
