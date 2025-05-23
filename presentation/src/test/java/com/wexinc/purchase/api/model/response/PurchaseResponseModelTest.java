package com.wexinc.purchase.api.model.response;

import com.wexinc.purchase.api.shared.constant.Constantes;
import com.wexinc.purchase.api.shared.constant.ConstantsPresentation;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PurchaseResponseModelTest {

  private static final PurchaseResponseModel ACTUAL =
      new PurchaseResponseModel(
          Constantes.LONG_MIN_VALUE,
          StringUtils.EMPTY,
          Constantes.FIXED_LOCAL_DATE_TIME,
          BigDecimal.ZERO);

  @Test
  void testId() {
    Assertions.assertEquals(
        Constantes.LONG_MIN_VALUE,
        PurchaseResponseModelTest.ACTUAL.id(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testDescription() {
    Assertions.assertEquals(
        StringUtils.EMPTY,
        PurchaseResponseModelTest.ACTUAL.description(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testTransactionDate() {
    Assertions.assertEquals(
        Constantes.FIXED_LOCAL_DATE_TIME,
        PurchaseResponseModelTest.ACTUAL.transactionDate(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }

  @Test
  void testAmount() {
    Assertions.assertEquals(
        BigDecimal.ZERO.setScale(ConstantsPresentation.TWO, RoundingMode.HALF_EVEN),
        PurchaseResponseModelTest.ACTUAL.amount(),
        Constantes.EXPECTED_THE_SAME_RESULT);
  }
}
