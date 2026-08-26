package com.wexinc.purchase.api.dto;

import com.wexinc.purchase.api.shared.constant.CoreTestConstants;
import com.wexinc.purchase.api.shared.fixture.PurchaseDTOFixture;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseDTOTest {

  @Test
  void testId() {
    Assertions.assertEquals(
        CoreTestConstants.LONG_MIN_VALUE,
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE.id(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testDescription() {
    Assertions.assertEquals(
        StringUtils.EMPTY,
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE.description(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testTransactionDate() {
    Assertions.assertEquals(
        CoreTestConstants.FIXED_LOCAL_DATE_TIME,
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE.transactionDate(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testAmount() {
    Assertions.assertEquals(
        BigDecimal.ZERO,
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO_FIXTURE.amount(),
        CoreTestConstants.EXPECTED_THE_SAME_RESULT);
  }
}
