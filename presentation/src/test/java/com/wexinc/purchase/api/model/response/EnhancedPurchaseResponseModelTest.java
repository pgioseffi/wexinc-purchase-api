package com.wexinc.purchase.api.model.response;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnhancedPurchaseResponseModelTest {

  private static final EnhancedPurchaseResponseModel ACTUAL =
      new EnhancedPurchaseResponseModel(
          CoreTestConstants.LONG_MIN_VALUE,
          StringUtils.EMPTY,
          CoreTestConstants.FIXED_LOCAL_DATE_TIME,
          BigDecimal.ZERO,
          List.of());

  @Test
  void testId() {
    Assertions.assertEquals(
        CoreTestConstants.LONG_MIN_VALUE,
        EnhancedPurchaseResponseModelTest.ACTUAL.id(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testDescription() {
    Assertions.assertEquals(
        StringUtils.EMPTY,
        EnhancedPurchaseResponseModelTest.ACTUAL.description(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testTransactionDate() {
    Assertions.assertEquals(
        CoreTestConstants.FIXED_LOCAL_DATE_TIME,
        EnhancedPurchaseResponseModelTest.ACTUAL.transactionDate(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testAmount() {
    Assertions.assertEquals(
        CoreTestConstants.ZERO_WITH_SCALE_TWO,
        EnhancedPurchaseResponseModelTest.ACTUAL.amount(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testExchangeRate() {
    Assertions.assertEquals(
        List.of(),
        EnhancedPurchaseResponseModelTest.ACTUAL.exchangeRateData(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
