package com.wexinc.purchase.api.resource;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.constant.Country;
import java.math.BigDecimal;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ExchangeRateDataResourceTest {

  private static final ExchangeRateDataResource ACTUAL =
      new ExchangeRateDataResource(
          Country.BRAZIL.name(), BigDecimal.ZERO, CoreTestConstants.FIXED_LOCAL_DATE);

  @Test
  void testCountry() {
    Assertions.assertEquals(
        Country.BRAZIL.name(),
        ExchangeRateDataResourceTest.ACTUAL.country(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testExchangeRate() {
    Assertions.assertEquals(
        BigDecimal.ZERO,
        ExchangeRateDataResourceTest.ACTUAL.exchangeRate(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testEffectiveDate() {
    Assertions.assertEquals(
        CoreTestConstants.FIXED_LOCAL_DATE,
        ExchangeRateDataResourceTest.ACTUAL.effectiveDate(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
