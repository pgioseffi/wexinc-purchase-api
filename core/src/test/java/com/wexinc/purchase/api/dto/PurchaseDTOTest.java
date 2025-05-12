package com.wexinc.purchase.api.dto;

import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.util.PurchaseDTOFixture;
import java.math.BigDecimal;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseDTOTest {

  @Test
  void testId() {
    Assertions.assertEquals(
        Constantes.LONG_MIN_VALUE,
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.id(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testDescription() {
    Assertions.assertEquals(
        StringUtils.EMPTY,
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.description(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testTransactionDate() {
    Assertions.assertEquals(
        Constantes.FIXED_LOCAL_DATE_TIME,
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.transactionDate(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testAmount() {
    Assertions.assertEquals(
        BigDecimal.ZERO,
        PurchaseDTOFixture.ACTUAL_PURCHASE_DTO.amount(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
