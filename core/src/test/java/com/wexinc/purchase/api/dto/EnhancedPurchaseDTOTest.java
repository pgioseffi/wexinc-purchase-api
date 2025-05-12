package com.wexinc.purchase.api.dto;

import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;
import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class EnhancedPurchaseDTOTest {

  @Test
  void testId() {
    Assertions.assertEquals(
        Constantes.LONG_MIN_VALUE,
        PurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO.id(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testDescription() {
    Assertions.assertEquals(
        StringUtils.EMPTY,
        PurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO.description(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testTransactionDate() {
    Assertions.assertEquals(
        Constantes.FIXED_LOCAL_DATE_TIME,
        PurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO.transactionDate(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testAmount() {
    Assertions.assertEquals(
        BigDecimal.ZERO,
        PurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO.amount(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testExchangeRate() {
    Assertions.assertEquals(
        List.of(),
        PurchaseDTOFixture.ACTUAL_ENHANCED_PURCHASE_DTO.exchangeRateData(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
