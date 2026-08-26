package com.wexinc.purchase.api.dto;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.constant.Country;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExchangeRateDataDTOTest {

  private static final ExchangeRateDataDTO ACTUAL =
      new ExchangeRateDataDTO(
          Country.BRAZIL.name(), BigDecimal.ZERO, CoreTestConstants.FIXED_LOCAL_DATE);

  @Test
  void testCountry() {
    Assertions.assertEquals(
        Country.BRAZIL.name(),
        ExchangeRateDataDTOTest.ACTUAL.country(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testDescription() {
    Assertions.assertEquals(
        CoreTestConstants.FIXED_LOCAL_DATE,
        ExchangeRateDataDTOTest.ACTUAL.effectiveDate(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testExchangeRate() {
    Assertions.assertEquals(
        BigDecimal.ZERO,
        ExchangeRateDataDTOTest.ACTUAL.exchangeRate(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
