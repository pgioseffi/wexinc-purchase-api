package com.wexinc.purchase.api.dto;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.fixture.EnhancedPurchaseDTOFixture;
import com.wexinc.purchase.api.shared.fixture.ExchangeRateDataDTOFixture;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnhancedPurchaseDTOTest {

  @Test
  void testId() {
    Assertions.assertEquals(
        CoreTestConstants.LONG_MIN_VALUE,
        EnhancedPurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE.id(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testDescription() {
    Assertions.assertEquals(
        StringUtils.EMPTY,
        EnhancedPurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE.description(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testTransactionDate() {
    Assertions.assertEquals(
        CoreTestConstants.FIXED_LOCAL_DATE_TIME,
        EnhancedPurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE.transactionDate(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testAmount() {
    Assertions.assertEquals(
        BigDecimal.ZERO,
        EnhancedPurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE.amount(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testExchangeRate() {
    Assertions.assertIterableEquals(
        List.of(ExchangeRateDataDTOFixture.EXCHANGE_RATE_DATA_DTO_FIXTURE),
        EnhancedPurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO_FIXTURE.exchangeRateData(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
